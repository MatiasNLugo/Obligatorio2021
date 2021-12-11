package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            throw new ExcepcionLogica("La tarea no puede ser nula.");
        }
        try {
            Date fechaLimite = new SimpleDateFormat("dd/MM/yyyy").parse(tarea.getFechaLimite());
            Date fechaActual = Calendar.getInstance().getTime();
            if (fechaLimite.before(fechaActual)){
                throw new ExcepcionLogica("La fecha Limite de la tarea no puede ser anterior a la fecha actual");
            }
        } catch (Exception ex){
            throw new ExcepcionLogica("Error en el formato de la fecha.");
        }
        if(tarea.getDescipcion().isEmpty()){
            throw new ExcepcionLogica("La Descripción no puede estar vacía.");
        }

        if(tarea.getUnEvento() == null){
            throw new ExcepcionLogica("El Evento relacionado al gasto no puede ser nulo.");
        }

    }


}
