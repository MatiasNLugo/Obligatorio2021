package com.matiaslugo.obligatorio2021.DataTypes;

public class Tarea {
    private int idTarea;
    private String descipcion;
    private String fechaLimite;
    private Evento unEvento;

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

    public Evento getUnEvento() {
        return unEvento;
    }

    public void setUnEvento(Evento unEvento) {
        this.unEvento = unEvento;
    }

    public Tarea(int idTarea, String descipcion, String fechaLimite, Evento unEvento) {
        this.idTarea = idTarea;
        this.descipcion = descipcion;
        this.fechaLimite = fechaLimite;
        this.unEvento = unEvento;
    }

    public Tarea() {
        this.idTarea = 0;
        this.descipcion = "N/D";
        this.fechaLimite = "N/D";
        this.unEvento = new Evento();
    }
}
