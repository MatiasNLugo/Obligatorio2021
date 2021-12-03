package com.matiaslugo.obligatorio2021.persistencia;

import android.content.Context;

public class FabricaPersistencia {

    public static  IPersistenciaCliente getPersistenciaCliente(Context contexto){
        return PersistenciaCliente.getInstance(contexto);
    }

    public static  IPersistenciaEvento getPersistenciaEvento(Context contexto){
        return PersistenciaEvento.getInstance(contexto);
    }

    public static  IPeristenciaReunion getPeristenciaReunion(Context contexto){
        return PersistenciaReunion.getInstance(contexto);
    }

    public static IPersistenciaGasto getPersistenciaGasto(Context contexto) {
        return PersistenciaGasto.getInstance(contexto);
    }

    public static  IPeristenciaTarea getPersistenciaTarea(Context contexto){
        return PersistenciaTarea.getInstance(contexto);
    }


}
