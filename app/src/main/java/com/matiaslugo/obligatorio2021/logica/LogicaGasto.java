package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

public class LogicaGasto {
    private static LogicaGasto instancia;

    public static LogicaGasto getInstancia() {
        if (instancia == null) {
            instancia = new LogicaGasto();
        }
        return instancia;
    }

    private LogicaGasto(){

    }

    public void validarGasto(DTGasto gasto) throws ExcepcionPersonalizada {
        if (gasto == null){
            throw new ExcepcionLogica("El gato no puede ser nulo.");
        }
        if (gasto.getMonto() < 0 ){
            throw new ExcepcionLogica("El monto no puede ser negativo.");
        }
        if ( gasto.getMotivo().isEmpty()){
            throw new ExcepcionLogica("El Motivo del Gasto no puede estar vacío.");
        }
        if(gasto.getProveedor().isEmpty()){
            throw new ExcepcionLogica("El Proveedor del Gasto no puede estar vacío.");
        }
        if(gasto.getUnEvento() == null){
            throw new ExcepcionLogica("El Evento asociado al gasto no puede ser nulo.");
        }
    }
}

