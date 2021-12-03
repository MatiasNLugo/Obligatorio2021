package com.matiaslugo.obligatorio2021.persistencia;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IPersistenciaEvento {

    long insertarEvento(DTEvento evento) throws ExcepcionPersonalizada;
    ArrayList<DTEvento> listaEvento() throws ExcepcionPersonalizada;
}
