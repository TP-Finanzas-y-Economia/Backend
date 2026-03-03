package com.crediapp.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EntidadFinanciera")
public class EntidadFinanciera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreEntidad;
    private Double tasaSeguroDes;
    private Double tasaSeguroRie;

    public EntidadFinanciera() {
    }

    public EntidadFinanciera(Integer id, String nombreEntidad, Double tasaSeguroDes, Double tasaSeguroRie) {
        this.id = id;
        this.nombreEntidad = nombreEntidad;
        this.tasaSeguroDes = tasaSeguroDes;
        this.tasaSeguroRie = tasaSeguroRie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public Double getTasaSeguroDes() {
        return tasaSeguroDes;
    }

    public void setTasaSeguroDes(Double tasaSeguroDes) {
        this.tasaSeguroDes = tasaSeguroDes;
    }

    public Double getTasaSeguroRie() {
        return tasaSeguroRie;
    }

    public void setTasaSeguroRie(Double tasaSeguroRie) {
        this.tasaSeguroRie = tasaSeguroRie;
    }
}
