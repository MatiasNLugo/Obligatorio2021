package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;

import java.util.ArrayList;

public class ControladorMantenimientoEvento implements IControladorMantenimientoEvento {

    private static ControladorMantenimientoEvento instancia;

    public static ControladorMantenimientoEvento getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ControladorMantenimientoEvento(context);
        }
        return instancia;
    }

    private Context context;

    private ControladorMantenimientoEvento(Context context){
        this.context = context;
    }


    @Override
    public long insertarEvento(DTEvento evento) throws ExcepcionPersonalizada {
           LogicaEvento.getInstancia().validarEvento(evento);
           return FabricaPersistencia.getPersistenciaEvento(context).insertarEvento(evento);
       }

    @Override
    public ArrayList<DTEvento> listaEvento() throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaEvento(context).listaEvento();
    }

    @Override
    public long modificarEvento(DTEvento evento) throws ExcepcionPersonalizada {
            LogicaEvento.getInstancia().validarEvento(evento);
            return FabricaPersistencia.getPersistenciaEvento(context).modificarEvento(evento);

    }

    @Override
    public long eliminarEvento(int idEvento) throws ExcepcionPersonalizada {
            return FabricaPersistencia.getPersistenciaEvento(context).eliminarEvento(idEvento);
    }

    @Override
    public boolean verificarDependencia(int idCliente) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaEvento(context).verificarDependencia(idCliente);
    }



}
