package com.crediapp.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cronograma_pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronogramaPagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "simulacion_id")
    private Simulacion simulacion;

    private Double tcea; // Tasa Costo Efectiva Anual (Resultado final)
    private Double montoPrestamoNeto; // ValorInmueble - (Inicial + Bono)
    private Double totalIntereses;
    private Double montoTotalPagado;
    Double van;
    Double tir;
}