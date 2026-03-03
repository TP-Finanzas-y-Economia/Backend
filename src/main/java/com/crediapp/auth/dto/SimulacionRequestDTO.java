package com.crediapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacionRequestDTO {
    private Integer entidadId;
    private Double valorVivienda;
    private Double cuotaInicial;
    private Double bonoMonto;
    private Integer plazoMeses;
    private Double sueldoNeto;
    private Integer mesesGracia;
    private String tipoGracia;
    private Double tipoCambio;
}
