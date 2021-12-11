package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTTareaListItem implements Serializable {
    public String descripcion;
    public String fechaLimite;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public DTTareaListItem(String descripcion, String fechaLimite) {
        setDescripcion(descripcion);
        setFechaLimite(fechaLimite);
    }

    public DTTareaListItem(){
        descripcion = "";
        fechaLimite = "";
    }

    @Override
    public String toString() {
        return "DTTareaListItem{" +
                "Descripcion ='" + descripcion + '\'' +
                ", FechaLimite ='" + fechaLimite + '\'' +
                '}';
    }
}
