package com.matiaslugo.obligatorio2021.compartidos.excepciones;

public class ExcepcionPersonalizada extends Exception {

    public ExcepcionPersonalizada(){

    }

    public ExcepcionPersonalizada(String mensaje){
        super(mensaje);
    }

    public ExcepcionPersonalizada(String mensaje, Exception excepcionInterna){
        super(mensaje, excepcionInterna);

    }
}
