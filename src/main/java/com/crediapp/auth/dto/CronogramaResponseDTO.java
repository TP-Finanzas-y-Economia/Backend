package com.crediapp.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CronogramaResponseDTO {
    private Double montoPrestamoNeto;
    private Double tcea;
    private Double totalIntereses;
    private Double montoTotalPagado;
    private List<CuotaDTO> cuotas;
}
