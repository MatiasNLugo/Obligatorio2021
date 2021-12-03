package com.matiaslugo.obligatorio2021.compartidos.datatypes;

import java.io.Serializable;

public class DTComercial extends DTCliente implements Serializable {
    private String rut;
    private String razonSocial;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public DTComercial(int idCliente, String direccion, String telefono, String correo, String rut, String razonSocial) {
        super(idCliente, direccion, telefono, correo);
        setRut(rut);
        setRazonSocial(razonSocial);
    }

    public DTComercial(String rut, String razonSocial) {
        this.rut = rut;
        this.razonSocial = razonSocial;
    }
    public DTComercial(){
        super();
        this.rut = "S/D";
        this.razonSocial = "S/D";


    }
}
