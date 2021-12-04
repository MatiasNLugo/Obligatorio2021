package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTTarea implements Serializable {
    private int idTarea;
    private String descipcion;
    private String fechaLimite;
    private boolean realizada;
    private DTEvento unEvento;

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public DTEvento getUnEvento() {
        return unEvento;
    }

    public void setUnEvento(DTEvento unEvento) {
        this.unEvento = unEvento;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public DTTarea(int idTarea, String descipcion, String fechaLimite, Boolean realizada, DTEvento unEvento) {
        this.idTarea = idTarea;
        this.descipcion = descipcion;
        this.fechaLimite = fechaLimite;
        this.unEvento = unEvento;
        this.realizada = realizada;
    }

    public DTTarea() {
        this.idTarea = 0;
        this.descipcion = "N/D";
        this.fechaLimite = "N/D";
        this.unEvento = new DTEvento();
    }
}
