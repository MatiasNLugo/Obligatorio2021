package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
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
    public long insertarReunion(DTReunion reunion) throws ExcepcionPersonalizada {
       if(!LogicaReunion.getInstancia().validarReunionesSolapadas
               (reunion,FabricaPersistencia.getPeristenciaReunion(context).listaReuniones())){
           LogicaReunion.getInstancia().validarReunion(reunion);
           return FabricaPersistencia.getPeristenciaReunion(context).insertarReunion(reunion);
       } else {
           throw  new ExcepcionPersonalizada("Hay Reuniones con la misma fecha y hora.");
       }
    }

    @Override
    public Boolean modificarReunion(DTReunion reunion) throws ExcepcionPersonalizada {
       if(!LogicaReunion.getInstancia().validarReunionesSolapadas
                (reunion,FabricaPersistencia.getPeristenciaReunion(context).listaReuniones())
        ){
           LogicaReunion.getInstancia().validarReunion(reunion);
           return FabricaPersistencia.getPeristenciaReunion(context).modificarReunion(reunion);
       } else {
           throw  new ExcepcionLogica("Hay Reuniones con la misma fecha y hora.");
       }

    }

    public ArrayList<DTReunion> listarPendientes(ArrayList<DTReunion> reuniones)  throws ExcepcionPersonalizada {
        return LogicaReunion.getInstancia().listarReunionesPendientes(reuniones);
    }
}
