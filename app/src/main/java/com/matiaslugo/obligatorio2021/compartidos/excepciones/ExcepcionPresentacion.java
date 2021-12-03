package com.matiaslugo.obligatorio2021.compartidos.excepciones;

public class ExcepcionPresentacion extends ExcepcionPersonalizada {

    public ExcepcionPresentacion(){

    }

    public ExcepcionPresentacion(String mensaje){
        super(mensaje);
    }

    public ExcepcionPresentacion(String mensaje, Exception excepcionInterna){
        super(mensaje, excepcionInterna);

    }
}
