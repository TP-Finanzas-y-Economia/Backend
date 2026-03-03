package com.crediapp.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entidad_financiera")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EntidadFinanciera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreEntidad;
    private Double tasaEfectivaAnual;
    private Double tasaSeguroDesgravamen;
    private Double tasaSeguroInmueble;

    // Nuevos campos de validación de riesgo
    private Double ingresoMinimoRequerido;
    private Double montoMaximoPrestamo;
    private Integer periodoGraciaMaximo; // Usualmente 6 meses

    // Límites de vivienda (Importante para CRC)
    private Double precioMinVivienda;
    private Double precioMaxVivienda;

    private Boolean admiteCRC; // Identifica si opera con montos > 362,100
}
