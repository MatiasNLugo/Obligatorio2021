package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;

import java.util.ArrayList;

public class ControladorMantenimientoGasto implements IControladorMantenimientoGasto {
    private static ControladorMantenimientoGasto instancia;

    public static ControladorMantenimientoGasto getInstancia(Context context) {
        if (instancia == null) {
            instancia = new ControladorMantenimientoGasto(context);
        }
        return instancia;
    }

    private Context context;

    private ControladorMantenimientoGasto(Context context){
        this.context = context.getApplicationContext();
    }



    @Override
    public ArrayList<DTGasto> listaGastos(int idEvento) throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaGasto(context).listaGastos(idEvento);
    }

    @Override
    public long insertarGasto(DTGasto gasto) throws ExcepcionPersistencia {
        return FabricaPersistencia.getPersistenciaGasto(context).insertarGasto(gasto);
    }
}
