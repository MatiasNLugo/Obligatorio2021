package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;
import com.matiaslugo.obligatorio2021.persistencia.IPeristenciaReunion;

import java.util.ArrayList;

public class ControladorMantenimientoReunion implements IControladorMantenimientoReunion {

    private static ControladorMantenimientoReunion instancia;

    public static ControladorMantenimientoReunion getInstancia(Context context) {
        if (instancia == null){
            instancia = new ControladorMantenimientoReunion(context);
        }
        return instancia;
    }

    private Context context;

    private ControladorMantenimientoReunion(Context contexto){
        this.context = contexto.getApplicationContext();

    }


    @Override
    public ArrayList<DTReunion> listaReuniones(int idEvento) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPeristenciaReunion(context).listaReuniones(idEvento);
    }

    @Override
    public long insertarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada {
        LogicaReunion.getInstancia().validarReunion(DTReunion);
        return FabricaPersistencia.getPeristenciaReunion(context).insertarReunion(DTReunion);

    }

    @Override
    public Boolean modificarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada {
        LogicaReunion.getInstancia().validarReunion(DTReunion);
        return FabricaPersistencia.getPeristenciaReunion(context).modificarReunion(DTReunion);
    }
}
