package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTParticular extends DTCliente implements Serializable {
    private String cedula;
    private String nombre;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DTParticular(int idCliente, String direccion, String telefono, String correo, String cedula, String nombre) {
        super(idCliente, direccion, telefono, correo);
        setCedula(cedula);
        setNombre(nombre);
    }

    public DTParticular(String cedula, String nombre) {
        super();
        setCedula(cedula);
        setNombre(nombre);
    }

    public DTParticular(){
        super();
        this.cedula = "N/D";
        this.nombre = "N/D";
    }
}
