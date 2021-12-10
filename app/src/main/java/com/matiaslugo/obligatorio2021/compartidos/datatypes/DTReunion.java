package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("deprecation")
public class DTReunion implements Serializable {
    private int idReunion;
    private String descripcion;
    private String objetivo;
    private String fecha;
    private String hora;
    private String lugar;
    private boolean notificar;
    private DTEvento evento;

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

    public DTEvento getEvento() {
        return evento;
    }

    public void setEvento(DTEvento evento) {
        this.evento = evento;
    }

    public DTReunion(int idReunion, String descripcion, String objetivo, String fecha, String hora, String lugar, boolean notificar, DTEvento evento) {
        setIdReunion(idReunion);
        setDescripcion(descripcion);
        setObjetivo(objetivo);
        setFecha(fecha);
        setHora(hora);
        setLugar(lugar);
        setNotificar(notificar);
        setEvento(evento);
    }

    public DTReunion() {
        this.idReunion = 0;
        this.descripcion = "N/D";
        this.objetivo = "N/D";
        this.fecha = "N/D";
        this.hora = "N/D";
        this.lugar = "N/D";
        this.notificar = true;
        this.evento = new DTEvento();
    }
}
