package com.crediapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacionRequestDTO {
    private Integer entidadId;        // ID del banco elegido
    private Double valorVivienda;     // Precio del inmueble (S/ o $)
    private Double cuotaInicial;      // Monto de la inicial (sin bono)
    private Double bonoMonto;         // El monto del BBP calculado previamente
    private Integer plazoMeses;       // De 60 a 300
    private Double sueldoNeto;        // Para validar ingreso mínimo
    private Integer mesesGracia;      // 0 a 6
    private String tipoGracia;        // "TOTAL", "PARCIAL" o "SIN_GRACIA"
    private Double tipoCambio;        // Opcional, si el usuario quiere ver en USD
}
