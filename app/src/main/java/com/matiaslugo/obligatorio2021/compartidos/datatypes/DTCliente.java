package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTCliente implements Serializable {
    private int idCliente;
    private String direccion;
    private String telefono;
    private String correo;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public DTCliente(int idCliente, String direccion, String telefono, String correo) {
        setIdCliente(idCliente);
        setDireccion(direccion);
        setTelefono(telefono);
        setCorreo(correo);
    }
    public DTCliente(){
        this.idCliente = 0;
        this.direccion = "N/D";
        this.telefono = "N/D";
        this.correo = "N/D";
    }
}
