package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

class LogicaReunion {
    private static LogicaReunion instancia;

    public static LogicaReunion getInstancia() {
        if (instancia == null){
            instancia = new LogicaReunion();
        }
        return instancia;
    }

    private LogicaReunion(){

    }

    public void validarReunion(DTReunion reunion) throws ExcepcionPersonalizada {
        if (reunion == null){
            throw  new ExcepcionLogica();
        }
    }

}
