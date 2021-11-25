package com.matiaslugo.obligatorio2021.DataTypes;

import java.io.Serializable;

public class Evento implements Serializable {
    private int idEvento;
    private String fecha;
    private String hora;
    private String duracion;
    private String titulo;
    private int tipo;
    private int cantAsistentes;
    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCantAsistentes() {
        return cantAsistentes;
    }

    public void setCantAsistentes(int cantAsistentes) {
        this.cantAsistentes = cantAsistentes;
    }

    public Evento(int idEvento, String fecha, String hora, String duracion, String titulo, int tipo, int cantAsistentes) {
        setIdEvento(idEvento);
        setFecha(fecha);
        setHora(hora);
        setDuracion(duracion);
        setTitulo(titulo);
        setTipo(tipo);
        setCantAsistentes(cantAsistentes);
    }

    public Evento(){
        this.idEvento = 0;
        this.fecha = "N/D";
        this.hora = "N/D";
        this.duracion = "N/D";
        this.titulo = "N/D";
        this.tipo = 1;
        this.cantAsistentes = 0;
        idCliente = 1;
    }

}
