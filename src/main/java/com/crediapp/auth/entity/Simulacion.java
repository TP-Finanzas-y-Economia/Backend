package com.crediapp.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "simulaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Simulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private EntidadFinanciera entidad;

    private Double valorInmueble; // Entre 68,000 y 488,800
    private Double porcentajeCuotaInicial; // Mínimo 7.5%
    private Double montoBonoAplicado; // Calculado según tipo de vivienda y rango

    private Integer plazoMeses; // 60 a 300 meses (5-25 años)
    private Integer mesesGracia;
    private String tipoGracia; // "TOTAL", "PARCIAL", "SIN_GRACIA"

    private String monedaSimulacion; // "PEN" o "USD"
    private Double tipoCambioUsado; // El valor que ingrese el usuario

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaSimulacion;
}
