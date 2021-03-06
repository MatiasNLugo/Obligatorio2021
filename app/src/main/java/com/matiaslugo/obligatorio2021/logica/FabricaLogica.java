package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

public class FabricaLogica {

    public static IControladorMantenimientoReunion getControladorMantenimientoReunion(Context context){
        return ControladorMantenimientoReunion.getInstancia(context);
    }

    public static IControladorMantenimientoEvento getControladorMantenimientoEvento(Context context){
        return ControladorMantenimientoEvento.getInstancia(context);
    }

    public static IControladorMantenimientoCliente getControladorMantenimientoCliente(Context context){
        return ControladorMantenimientoCliente.getInstancia(context);
    }

    public static IControladorMantenimientoGasto getControladorMantenimientoGasto(Context context){
        return ControladorMantenimientoGasto.getInstancia(context);
    }

    public static IControladorMantenimientoTarea getControladorMantenimientoTarea(Context context){
        return ControladorMantenimientoTarea.getInstancia(context);
    }
}
