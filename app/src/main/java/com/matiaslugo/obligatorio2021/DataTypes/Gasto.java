package com.matiaslugo.obligatorio2021.DataTypes;

import java.io.Serializable;

public class Gasto implements Serializable {
    private int idGasto;
    private String motivo;
    private String proveedor;
    private float monto;
    private Evento unEvento;

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

    public Evento getUnEvento() {
        return unEvento;
    }

    public void setUnEvento(Evento unEvento) {
        this.unEvento = unEvento;
    }

    public Gasto(int idGasto, String motivo, String proveedor, float monto, Evento unEvento) {
        setIdGasto(idGasto);
        setMotivo(motivo);
        setProveedor(proveedor);
        setMonto(monto);
        setUnEvento(unEvento);
    }

    public Gasto() {
        this.idGasto = 0;
        this.motivo = "N/D";
        this.proveedor = "N/D";
        this.monto = 0;
        this.unEvento = new Evento();
    }
}
