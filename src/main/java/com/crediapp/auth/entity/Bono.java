package com.crediapp.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bono")
public class Bono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Double rangoMin;
    private Double rangoMax;
    private Double monto;

    public Bono() {
    }

    public Bono(Integer id, String nombre, Double rangoMin, Double rangoMax, Double monto) {
        this.id = id;
        this.nombre = nombre;
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(Double rangoMin) {
        this.rangoMin = rangoMin;
    }

    public Double getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(Double rangoMax) {
        this.rangoMax = rangoMax;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
