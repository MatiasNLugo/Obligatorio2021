package com.matiaslugo.obligatorio2021.compartidos.datatypes;

public class DTTarea {
    private int idTarea;
    private String descipcion;
    private String fechaLimite;
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

    public DTTarea(int idTarea, String descipcion, String fechaLimite, DTEvento unEvento) {
        this.idTarea = idTarea;
        this.descipcion = descipcion;
        this.fechaLimite = fechaLimite;
        this.unEvento = unEvento;
    }

    public DTTarea() {
        this.idTarea = 0;
        this.descipcion = "N/D";
        this.fechaLimite = "N/D";
        this.unEvento = new DTEvento();
    }
}
