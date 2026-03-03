package com.crediapp.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rangos_bono")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangoBono {
    @Id
    private String id; // R1, R2, R3, R4, R5 [cite: 7]

    private Double precioMinimo; // Ej: 68800 [cite: 7]
    private Double precioMaximo; // Ej: 98100 [cite: 7]

    // Columnas de la imagen
    private Double bbpTradicional;
    private Double bbpSostenible;
    private Double bbpIntegradorTradicional;
    private Double bbpIntegradorSostenible;
}
