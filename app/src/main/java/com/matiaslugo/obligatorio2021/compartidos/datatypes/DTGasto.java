package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTGasto implements Serializable {
    private int idGasto;
    private String motivo;
    private String proveedor;
    private float monto;
    private DTEvento unEvento;

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public DTEvento getUnEvento() {
        return unEvento;
    }

    public void setUnEvento(DTEvento unEvento) {
        this.unEvento = unEvento;
    }

    public DTGasto(int idGasto, String motivo, String proveedor, float monto, DTEvento unEvento) {
        setIdGasto(idGasto);
        setMotivo(motivo);
        setProveedor(proveedor);
        setMonto(monto);
        setUnEvento(unEvento);
    }

    public DTGasto() {
        this.idGasto = 0;
        this.motivo = "N/D";
        this.proveedor = "N/D";
        this.monto = 0;
        this.unEvento = new DTEvento();
    }
}
