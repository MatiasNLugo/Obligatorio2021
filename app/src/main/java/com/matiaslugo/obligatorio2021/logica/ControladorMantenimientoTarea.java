package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;

import java.util.ArrayList;

public class ControladorMantenimientoTarea implements IControladorMantenimientoTarea {
    private static ControladorMantenimientoTarea instancia;

    public static ControladorMantenimientoTarea getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ControladorMantenimientoTarea(context);
        }
        return instancia;
    }

    private Context context;

    private ControladorMantenimientoTarea(Context context){
        this.context = context;
    }


    @Override
    public ArrayList<DTTarea> listaTareas(int idEvento) throws ExcepcionPersistencia {
        return FabricaPersistencia.getPersistenciaTarea(context).listaTareas(idEvento);
    }

    @Override
    public long insertarTarea(DTTarea tarea) throws ExcepcionPersonalizada {
        LogicaTarea.getInstancia().validarTarea(tarea);
        return FabricaPersistencia.getPersistenciaTarea(context).insertarTarea(tarea);
    }

    @Override
    public long cambiarEstadoTarea(DTTarea tarea) throws ExcepcionPersistencia {
        return FabricaPersistencia.getPersistenciaTarea(context).cambiarEstadoTarea(tarea);
    }


}
