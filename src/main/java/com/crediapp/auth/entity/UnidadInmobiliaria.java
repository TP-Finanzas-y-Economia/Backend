package com.crediapp.auth.entity;

package com.crediapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "unidad_inmobiliaria")
public class UnidadInmobiliaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valorVivienda;

    private String tipoVivienda;

    public UnidadInmobiliaria() {}

    public UnidadInmobiliaria(Integer id, Double valorVivienda, String tipoVivienda) {
        this.id = id;
        this.valorVivienda = valorVivienda;
        this.tipoVivienda = tipoVivienda;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getValorVivienda() { return valorVivienda; }
    public void setValorVivienda(Double valorVivienda) {
        this.valorVivienda = valorVivienda;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
}