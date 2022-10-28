package com.ulp.plantilla.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class Contrato implements Serializable {

    private int idContrato;
    private String fechaInicio;
    private String fechaFinal;
    //private Date fechaInicio;
    //private Date fechaFinal;
    private double alquilerMensual;
    private int inmuebleId;
    private int inquilinoId;
    private Inmueble propiedad;
    private Inquilino inquilino;

    public Contrato() {}

    public Contrato(int idContrato, String fechaInicio, String fechaFinal, double alquilerMensual, int inmuebleId, int inquilinoId, Inmueble propiedad, Inquilino inquilino) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.alquilerMensual = alquilerMensual;
        this.inmuebleId = inmuebleId;
        this.inquilinoId = inquilinoId;
        this.propiedad = propiedad;
        this.inquilino = inquilino;
    }

    /*
    public Contrato(int idContrato, Date fechaInicio, Date fechaFinal, double alquilerMensual, int inmuebleId, int inquilinoId) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.alquilerMensual = alquilerMensual;
        this.inmuebleId = inmuebleId;
        this.inquilinoId = inquilinoId;
    }
    */

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFin) {
        this.fechaFinal = fechaFin;
    }

    /*
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFin) {
        this.fechaFinal = fechaFin;
    }
    */

    public double getAlquilerMensual() {
        return alquilerMensual;
    }

    public void setAlquilerMensual(double alquilerMensual) {
        this.alquilerMensual = alquilerMensual;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public Inmueble getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Inmueble propiedad) {
        this.propiedad = propiedad;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return idContrato == contrato.idContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrato);
    }
}
