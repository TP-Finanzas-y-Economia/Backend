package com.crediapp.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Moneda")
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String simbolo;
    private Double tipoCambio;

    public Moneda() {
    }

    public Moneda(Integer id, String nombre, String simbolo, Double tipoCambio) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.tipoCambio = tipoCambio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
