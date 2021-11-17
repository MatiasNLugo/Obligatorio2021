package com.matiaslugo.obligatorio2021.DataTypes;

public class Particular extends Cliente{
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

    public Particular(int idCliente, String direccion, String telefono, String correo, String cedula, String nombre) {
        super(idCliente, direccion, telefono, correo);
        setCedula(cedula);
        setNombre(nombre);
    }

    public Particular(String cedula, String nombre) {
        super();
        setCedula(cedula);
        setNombre(nombre);
    }

    public Particular(){
        super();
        this.cedula = "N/D";
        this.nombre = "N/D";
    }
}
