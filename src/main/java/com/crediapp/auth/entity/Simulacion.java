package com.crediapp.auth.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "simulaciones")
public class Simulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSimulacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "idEntidad")
    private EntidadFinanciera entidadFinanciera;

    @ManyToOne
    @JoinColumn(name = "idMoneda")
    private Moneda moneda;

    @ManyToOne
    @JoinColumn(name = "idBono")
    private Bono bono;

    private Double valorInmueble;
    private Double cuotaInicial;
    private Double montoPrestamo;

    private Double tasaInteres;
    private String tipoTasa; // NOMINAL o EFECTIVA
    private Integer capitalizacion;

    private Integer plazoMeses;
    private Integer mesesGracia;
    private String tipoGracia; // TOTAL o PARCIAL

    @Temporal(TemporalType.DATE)
    private Date fechaSimulacion;

    public Simulacion() {
    }

    public Simulacion(Integer idSimulacion, User usuario, EntidadFinanciera entidadFinanciera, Moneda moneda, Bono bono, Double valorInmueble, Double cuotaInicial, Double montoPrestamo, Double tasaInteres, String tipoTasa, Integer capitalizacion, Integer plazoMeses, Integer mesesGracia, String tipoGracia, Date fechaSimulacion) {
        this.idSimulacion = idSimulacion;
        this.usuario = usuario;
        this.entidadFinanciera = entidadFinanciera;
        this.moneda = moneda;
        this.bono = bono;
        this.valorInmueble = valorInmueble;
        this.cuotaInicial = cuotaInicial;
        this.montoPrestamo = montoPrestamo;
        this.tasaInteres = tasaInteres;
        this.tipoTasa = tipoTasa;
        this.capitalizacion = capitalizacion;
        this.plazoMeses = plazoMeses;
        this.mesesGracia = mesesGracia;
        this.tipoGracia = tipoGracia;
        this.fechaSimulacion = fechaSimulacion;
    }

    public Integer getIdSimulacion() {
        return idSimulacion;
    }

    public void setIdSimulacion(Integer idSimulacion) {
        this.idSimulacion = idSimulacion;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Bono getBono() {
        return bono;
    }

    public void setBono(Bono bono) {
        this.bono = bono;
    }

    public Double getValorInmueble() {
        return valorInmueble;
    }

    public void setValorInmueble(Double valorInmueble) {
        this.valorInmueble = valorInmueble;
    }

    public Double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(Double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public Double getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(Double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public String getTipoTasa() {
        return tipoTasa;
    }

    public void setTipoTasa(String tipoTasa) {
        this.tipoTasa = tipoTasa;
    }

    public Integer getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(Integer capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public Date getFechaSimulacion() {
        return fechaSimulacion;
    }

    public void setFechaSimulacion(Date fechaSimulacion) {
        this.fechaSimulacion = fechaSimulacion;
    }

    public Integer getMesesGracia() {
        return mesesGracia;
    }

    public void setMesesGracia(Integer mesesGracia) {
        this.mesesGracia = mesesGracia;
    }

    public String getTipoGracia() {
        return tipoGracia;
    }

    public void setTipoGracia(String tipoGracia) {
        this.tipoGracia = tipoGracia;
    }
}
