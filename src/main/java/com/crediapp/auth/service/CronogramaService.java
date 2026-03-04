package com.crediapp.auth.service;

import com.crediapp.auth.dto.CronogramaResponseDTO;
import com.crediapp.auth.dto.CuotaDTO;
import com.crediapp.auth.dto.SimulacionRequestDTO;
import com.crediapp.auth.entity.EntidadFinanciera;
import com.crediapp.auth.repository.EntidadFinancieraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CronogramaService {

    @Autowired
    private EntidadFinancieraRepository entidadRepository;

    public CronogramaResponseDTO generarCronograma(SimulacionRequestDTO req) {
        // 1. Buscar la entidad financiera elegida
        EntidadFinanciera banco = entidadRepository.findById(req.getEntidadId())
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));
        //validacion de datos
        Double porcentajeReal = (req.getCuotaInicial() / req.getValorVivienda()) * 100;
        if (porcentajeReal < banco.getPorcentajeCuotaInicialMinima()) {
            throw new RuntimeException("La cuota inicial enviada (" + porcentajeReal +
                    "%) es menor al mínimo requerido por este banco (" +
                    banco.getPorcentajeCuotaInicialMinima() + "%).");
        }


        // 2. Validar Ingreso Mínimo
        if (req.getSueldoNeto() < banco.getIngresoMinimoRequerido()) {
            throw new RuntimeException("Su sueldo no alcanza para el ingreso mínimo requerido por " + banco.getNombreEntidad());
        }

        // 3. Monto Neto del Préstamo (Valor Vivienda - (Cuota Inicial + Bono BBP))
        Double montoPrestamo = req.getValorVivienda() - (req.getCuotaInicial() + req.getBonoMonto());

        if (montoPrestamo > banco.getMontoMaximoPrestamo()) {
            throw new RuntimeException("El monto solicitado excede el límite de préstamo de este banco.");
        }

        // 4. Conversión de Tasas (TEA a TEM y porcentajes a decimales)
        Double tea = banco.getTasaEfectivaAnual() / 100.0;
        Double tem = Math.pow(1 + tea, 1.0/12.0) - 1;
        Double tasaDesgravamenMes = banco.getTasaSeguroDesgravamen() / 100.0;
        Double tasaInmuebleMes = banco.getTasaSeguroInmueble() / 100.0;

        List<CuotaDTO> cuotas = new ArrayList<>();
        Double saldoInsoluto = montoPrestamo;
        Integer mesesGracia = req.getMesesGracia() != null ? req.getMesesGracia() : 0;
        Integer plazoEfectivo = req.getPlazoMeses() - mesesGracia;

        // Variables para totales
        Double totalIntereses = 0.0;
        Double totalSeguros = 0.0;
        Double montoTotalPagado = 0.0;

        // 5. Bucle de Generación de Cuotas
        for (int i = 1; i <= req.getPlazoMeses(); i++) {
            Double interesMes = saldoInsoluto * tem;
            Double seguroDesgravamen = saldoInsoluto * tasaDesgravamenMes;
            Double seguroInmueble = req.getValorVivienda() * tasaInmuebleMes;

            Double amortizacion = 0.0;
            Double cuotaMensual = 0.0;

            // Lógica según Periodo de Gracia
            if (i <= mesesGracia) {
                if ("TOTAL".equalsIgnoreCase(req.getTipoGracia())) {
                    // Gracia Total: Interés se capitaliza (suma a la deuda), no se paga nada
                    saldoInsoluto += interesMes;
                    amortizacion = 0.0;
                    cuotaMensual = 0.0; // En gracia total extrema no se paga ni seguros (según banco)
                } else if ("PARCIAL".equalsIgnoreCase(req.getTipoGracia())) {
                    // Gracia Parcial: Se paga interés + seguros, no hay amortización
                    amortizacion = 0.0;
                    cuotaMensual = interesMes + seguroDesgravamen + seguroInmueble;
                }
            }

            else {
                // Cálculo de Cuota Fija (Método Francés) - Se recalcula después de la gracia
                // para que el saldo llegue a cero exactamente.
                Double factor = Math.pow(1 + tem, req.getPlazoMeses() - i + 1);
                Double cuotaBase = saldoInsoluto * (tem * factor) / (factor - 1);

                amortizacion = cuotaBase - interesMes;
                cuotaMensual = cuotaBase + seguroDesgravamen + seguroInmueble;
                saldoInsoluto -= amortizacion;
            }

            // Acumular totales
            totalIntereses += interesMes;
            totalSeguros += (seguroDesgravamen + seguroInmueble);
            montoTotalPagado += cuotaMensual;

            // Agregar a la lista (Redondeo a 2 decimales para limpieza)
            cuotas.add(CuotaDTO.builder()
                    .numeroCuota(i)
                    .saldoInsoluto(redondear(Math.max(0, (i <= mesesGracia && "TOTAL".equalsIgnoreCase(req.getTipoGracia())) ? saldoInsoluto : saldoInsoluto + amortizacion)))
                    .interes(redondear(interesMes))
                    .amortizacion(redondear(amortizacion))
                    .seguroDesgravamen(redondear(seguroDesgravamen))
                    .seguroInmueble(redondear(seguroInmueble))
                    .montoCuota(redondear(cuotaMensual))
                    .build());
        }
        Double tceaFinal = calcularTceaAnual(montoPrestamo, cuotas);
        Double vanFinal = calcularVan(montoPrestamo, cuotas, banco.getTasaEfectivaAnual());

        // 3. Calculamos la TIR Mensual (opcional, si quieres mostrarla aparte de la anualizada)
        // La TCEA es la TIR anualizada. La TIR mensual sería:
        Double tirMensual = Math.round((Math.pow(1 + (tceaFinal / 100), 1.0 / 12.0) - 1) * 10000.0) / 100.0;

        // --- ACTUALIZAMOS EL BUILDER ---
        return CronogramaResponseDTO.builder()
                .montoPrestamoNeto(redondear(montoPrestamo))
                .totalIntereses(redondear(totalIntereses))
                .montoTotalPagado(redondear(montoTotalPagado))
                .tcea(tceaFinal)
                .van(vanFinal)       //
                .tir(tirMensual)     // (TIR Mensual)
                .cuotas(cuotas)
                .build();


    }

    private Double redondear(Double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    private Double calcularTceaAnual(Double montoPrestamo, List<CuotaDTO> cuotas) {
        double tasaEstimada = 0.01; // Empezamos probando con 1% mensual
        for (int i = 0; i < 100; i++) {
            double f = 0;
            double df = 0;
            for (CuotaDTO c : cuotas) {
                double t = c.getNumeroCuota();
                // TCEA evalúa el flujo de caja: cuánto sale de tu bolsillo (MontoCuota)
                f += c.getMontoCuota() / Math.pow(1 + tasaEstimada, t);
                df -= t * c.getMontoCuota() / Math.pow(1 + tasaEstimada, t + 1);
            }
            f -= montoPrestamo;
            double nuevaTasa = tasaEstimada - f / df;
            if (Math.abs(nuevaTasa - tasaEstimada) < 0.0000001) {
                tasaEstimada = nuevaTasa;
                break;
            }
            tasaEstimada = nuevaTasa;
        }
        // Convertimos la tasa mensual hallada a Anual (TCEA)
        double tceaAnual = Math.pow(1 + tasaEstimada, 12) - 1;
        return Math.round(tceaAnual * 10000.0) / 100.0; // Devuelve ej: 11.45
    }

    private Double calcularVan(Double montoPrestamo, List<CuotaDTO> cuotas, Double tea) {
        Double tem = Math.pow(1 + (tea / 100), 1.0/12.0) - 1;
        Double van = -montoPrestamo; // El préstamo es el flujo inicial (positivo para el usuario, pero negativo en la inversión)

        for (CuotaDTO cuota : cuotas) {
            van += cuota.getMontoCuota() / Math.pow(1 + tem, cuota.getNumeroCuota());
        }
        return redondear(van);
    }

}
