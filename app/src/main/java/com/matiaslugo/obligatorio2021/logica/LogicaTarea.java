package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

public class LogicaTarea {

    private static LogicaTarea instancia;

    public static LogicaTarea getInstancia() {
        if (instancia == null) {
            instancia = new LogicaTarea();
        }
        return instancia;
    }

    private LogicaTarea(){

    }

    public void validarTarea(DTTarea tarea) throws ExcepcionPersonalizada {
        if (tarea == null){
            throw new ExcepcionLogica();
        }
    }

}
