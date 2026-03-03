package com.crediapp.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuotaDTO {
    private Integer numeroCuota;
    private Double saldoInsoluto;
    private Double interes;
    private Double amortizacion;
    private Double seguroDesgravamen;
    private Double seguroInmueble;
    private Double montoCuota;
}
