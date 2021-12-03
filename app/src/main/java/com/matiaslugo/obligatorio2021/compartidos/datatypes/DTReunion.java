package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTReunion implements Serializable {
    private int idReunion;
    private String descripcion;
    private String objetivo;
    private String fecha;
    private String hora;
    private String lugar;
    private boolean notificar;

    private int idEvento;

    public int getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isNotificar() {
        return notificar;
    }

    public void setNotificar(boolean notificar) {
        this.notificar = notificar;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public DTReunion(int idReunion, String descripcion, String objetivo, String fecha, String hora, String lugar, boolean notificar, int idEvento) {
        setIdReunion(idReunion);
        setDescripcion(descripcion);
        setObjetivo(objetivo);
        setFecha(fecha);
        setHora(hora);
        setLugar(lugar);
        setNotificar(notificar);
        setIdEvento(idEvento);
    }

    public DTReunion() {
        this.idReunion = 0;
        this.descripcion = "N/D";
        this.objetivo = "N/D";
        this.fecha = "N/D";
        this.hora = "N/D";
        this.lugar = "N/D";
        this.notificar = true;
        this.idEvento = 1;
    }
}
