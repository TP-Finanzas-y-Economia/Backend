package com.crediapp.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cuotas")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuota;

    @ManyToOne
    @JoinColumn(name = "idCronograma")
    private CronogramaPagos cronogramaPagos;

    private Integer numeroCuota;
    private Double interesPeriodo;
    private Double amortizacionPeriodo;
    private Double seguroDesgravamen;
    private Double seguroInmueble;
    private Double saldoInsoluto;
    private Double cuotaTotal;

    public Cuota() {
    }

    public Cuota(Integer idCuota, CronogramaPagos cronogramaPagos,
                 Integer numeroCuota, Double interesPeriodo,
                 Double amortizacionPeriodo, Double seguroDesgravamen,
                 Double seguroInmueble, Double saldoInsoluto,
                 Double cuotaTotal) {

        this.idCuota = idCuota;
        this.cronogramaPagos = cronogramaPagos;
        this.numeroCuota = numeroCuota;
        this.interesPeriodo = interesPeriodo;
        this.amortizacionPeriodo = amortizacionPeriodo;
        this.seguroDesgravamen = seguroDesgravamen;
        this.seguroInmueble = seguroInmueble;
        this.saldoInsoluto = saldoInsoluto;
        this.cuotaTotal = cuotaTotal;
    }

    public Integer getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(Integer idCuota) {
        this.idCuota = idCuota;
    }

    public CronogramaPagos getCronogramaPagos() {
        return cronogramaPagos;
    }

    public void setCronogramaPagos(CronogramaPagos cronogramaPagos) {
        this.cronogramaPagos = cronogramaPagos;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Double getInteresPeriodo() {
        return interesPeriodo;
    }

    public void setInteresPeriodo(Double interesPeriodo) {
        this.interesPeriodo = interesPeriodo;
    }

    public Double getAmortizacionPeriodo() {
        return amortizacionPeriodo;
    }

    public void setAmortizacionPeriodo(Double amortizacionPeriodo) {
        this.amortizacionPeriodo = amortizacionPeriodo;
    }

    public Double getSeguroDesgravamen() {
        return seguroDesgravamen;
    }

    public void setSeguroDesgravamen(Double seguroDesgravamen) {
        this.seguroDesgravamen = seguroDesgravamen;
    }

    public Double getSeguroInmueble() {
        return seguroInmueble;
    }

    public void setSeguroInmueble(Double seguroInmueble) {
        this.seguroInmueble = seguroInmueble;
    }

    public Double getSaldoInsoluto() {
        return saldoInsoluto;
    }

    public void setSaldoInsoluto(Double saldoInsoluto) {
        this.saldoInsoluto = saldoInsoluto;
    }

    public Double getCuotaTotal() {
        return cuotaTotal;
    }

    public void setCuotaTotal(Double cuotaTotal) {
        this.cuotaTotal = cuotaTotal;
    }

    // getters y setters
}
