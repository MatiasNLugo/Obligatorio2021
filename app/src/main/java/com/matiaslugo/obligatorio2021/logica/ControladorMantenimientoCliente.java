package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;

import java.util.ArrayList;

public class ControladorMantenimientoCliente implements IControladorMantenimientoCliente {

    private static ControladorMantenimientoCliente instancia;

    public static ControladorMantenimientoCliente getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ControladorMantenimientoCliente(context);
        }
        return instancia;
    }

    private Context context;

    private ControladorMantenimientoCliente(Context context){
        this.context = context;
    }



    @Override
    public long insertarCliente(DTCliente cliente) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).insertarCliente(cliente);
    }

    @Override
    public ArrayList<DTCliente> listaClientes() throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).listaClientes();
    }

    @Override
    public boolean modificarCliente(DTCliente unCliente) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).modificarCliente(unCliente);
    }

    @Override
    public boolean eliminarCliente(int idCliente) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).eliminarCliente(idCliente);
    }

    @Override
    public boolean verificarDependenciaCliente(int idCliente) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).verificarDependenciaCliente(idCliente);
    }

    @Override
    public DTCliente buscarCliente(int id) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaCliente(context).buscarCliente(id);
    }
}
