package com.matiaslugo.obligatorio2021.persistencia;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.ControladorMantenimientoCliente;
import com.matiaslugo.obligatorio2021.logica.IControladorMantenimientoGasto;

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
        this.context = context;
    }



    @Override
    public ArrayList<DTGasto> listaGastos(int idEvento) throws ExcepcionPersonalizada {
        return null;
    }
}
