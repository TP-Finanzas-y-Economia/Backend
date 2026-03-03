package com.crediapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonoRequestDTO {
    private Double valorVivienda;
    private Boolean esSostenible;
    private Boolean esIntegrador;
}
