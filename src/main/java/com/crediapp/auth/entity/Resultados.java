package com.crediapp.auth.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Resultados")
public class Resultados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResultado;

    @ManyToOne
    @JoinColumn(name = "idSimulacion")
    private Simulacion simulacion;

    private Double van;
    private Double tir;
    private Double dcea;
    private Double montoTotalPagado;
    private Double totalIntereses;

    public Resultados() {
    }

    public Resultados(Integer idResultado, Simulacion simulacion, Double van, Double tir, Double dcea, Double montoTotalPagado, Double totalIntereses) {
        this.idResultado = idResultado;
        this.simulacion = simulacion;
        this.van = van;
        this.tir = tir;
        this.dcea = dcea;
        this.montoTotalPagado = montoTotalPagado;
        this.totalIntereses = totalIntereses;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public Simulacion getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(Simulacion simulacion) {
        this.simulacion = simulacion;
    }

    public Double getDcea() {
        return dcea;
    }

    public void setDcea(Double dcea) {
        this.dcea = dcea;
    }

    public Double getMontoTotalPagado() {
        return montoTotalPagado;
    }

    public void setMontoTotalPagado(Double montoTotalPagado) {
        this.montoTotalPagado = montoTotalPagado;
    }

    public Double getTotalIntereses() {
        return totalIntereses;
    }

    public void setTotalIntereses(Double totalIntereses) {
        this.totalIntereses = totalIntereses;
    }

    public Double getVan() {
        return van;
    }

    public void setVan(Double van) {
        this.van = van;
    }

    public Double getTir() {
        return tir;
    }

    public void setTir(Double tir) {
        this.tir = tir;
    }
}
