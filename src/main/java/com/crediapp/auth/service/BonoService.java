package com.crediapp.auth.service;

import com.crediapp.auth.dto.BonoRequestDTO;
import com.crediapp.auth.entity.RangoBono;
import com.crediapp.auth.repository.RangoBonoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonoService {

    @Autowired
    private RangoBonoRepository repository;

    public Double calcularMontoBono(BonoRequestDTO request) {



        // 1. Buscamos el rango (R1-R5) según el precio de la vivienda
        RangoBono rango = repository.findRangoByPrecio(request.getValorVivienda())
                .orElseThrow(() -> new EntityNotFoundException("El valor de la vivienda no aplica para los rangos del bono."));

        // 2. Si es R5, según la tabla del Fondo Mivivienda, el bono es S/ 0.0
        if ("R5".equalsIgnoreCase(rango.getId())) {
            return 0.0;
        }

        // 3. Lógica de selección de columna (usando get por ser Boolean objeto)
        // Usamos Boolean.TRUE.equals para evitar NullPointerException
        if (Boolean.TRUE.equals(request.getEsIntegrador())) {
            return Boolean.TRUE.equals(request.getEsSostenible()) ?
                    rango.getBbpIntegradorSostenible() : // Columna 4 de la imagen
                    rango.getBbpIntegradorTradicional(); // Columna 3 de la imagen
        } else {
            return Boolean.TRUE.equals(request.getEsSostenible()) ?
                    rango.getBbpSostenible() :           // Columna 2 de la imagen
                    rango.getBbpTradicional();           // Columna 1 de la imagen
        }
    }
}