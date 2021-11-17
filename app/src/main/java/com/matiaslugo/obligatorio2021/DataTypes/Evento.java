package com.matiaslugo.obligatorio2021.DataTypes;

public class Evento {
    private int idEvento;
    private String fecha;
    private String hora;
    private int duracion;
    private String titulo;
    private int tipo;
    private int cantAsistentes;

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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
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

    public Evento(int idEvento, String fecha, String hora, int duracion, String titulo, int tipo, int cantAsistentes) {
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
        this.duracion = 0;
        this.titulo = "N/D";
        this.tipo = 0;
        this.cantAsistentes = 0;
    }
}
