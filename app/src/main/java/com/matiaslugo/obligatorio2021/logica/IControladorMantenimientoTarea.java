package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IControladorMantenimientoTarea {
    ArrayList<DTTarea> listaTareas(int idEvento) throws ExcepcionPersistencia;
    long insertarTarea(DTTarea tarea ) throws ExcepcionPersonalizada;
    long cambiarEstadoTarea(DTTarea tarea) throws ExcepcionPersistencia;
}
