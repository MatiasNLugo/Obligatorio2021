package com.matiaslugo.obligatorio2021.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

import java.util.ArrayList;

public interface IPeristenciaTarea {

    ArrayList<DTTarea> listaTareas(int idEvento) throws ExcepcionPersistencia;
    long insertarTarea(DTTarea tarea ) throws ExcepcionPersistencia;
    long cambiarEstadoTarea(DTTarea tarea) throws ExcepcionPersistencia;

}
