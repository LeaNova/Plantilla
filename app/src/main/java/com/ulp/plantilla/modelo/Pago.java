package com.ulp.plantilla.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {

    private int numPago;
    private String fechaPago;
    private Double importe;
    private int contratoId;
    private String detalle;
    private Contrato contrato;

    public Pago() {}
    public Pago(int numPago, String fechaPago, Double importe, int contratoId, String detalle, Contrato contrato) {
        this.numPago = numPago;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.contratoId = contratoId;
        this.detalle = detalle;
        this.contrato = contrato;
    }
    public int getNumPago() {
        return numPago;
    }

    public void setNumPago(int numPago) {
        this.numPago = numPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
