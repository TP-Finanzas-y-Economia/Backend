package com.crediapp.auth.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cronograma_pagos")
public class CronogramaPagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCronograma;

    @ManyToOne
    @JoinColumn(name = "idSimulacion")
    private Simulacion simulacion;

    @Temporal(TemporalType.DATE)
    private Date fechaGeneracion;

    public CronogramaPagos() {
    }

    public CronogramaPagos(Integer idCronograma, Simulacion simulacion, Date fechaGeneracion) {
        this.idCronograma = idCronograma;
        this.simulacion = simulacion;
        this.fechaGeneracion = fechaGeneracion;
    }

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public Simulacion getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(Simulacion simulacion) {
        this.simulacion = simulacion;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}
