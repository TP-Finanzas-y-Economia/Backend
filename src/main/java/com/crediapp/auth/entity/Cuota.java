package com.crediapp.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cronograma_id")
    private CronogramaPagos cronograma;

    private Integer numeroCuota;
    private Double saldoInsoluto;
    private Double interes;
    private Double amortizacion;
    private Double seguroDesgravamen;
    private Double seguroInmueble;
    private Double montoCuotaSoles; // Suma de amortización + interés + seguros
    private Double montoCuotaDolares;//montoCuotaSoles / tipoCambioUsado
}