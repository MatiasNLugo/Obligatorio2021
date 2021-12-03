package com.matiaslugo.obligatorio2021.persistencia;

import android.content.Context;

import com.matiaslugo.obligatorio2021.logica.ControladorMantenimientoEvento;

public class ControladorMantenimientoTarea implements IControladorMantenimientoTarea{
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


}
