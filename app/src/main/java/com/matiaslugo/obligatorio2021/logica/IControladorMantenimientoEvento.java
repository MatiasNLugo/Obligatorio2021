package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IControladorMantenimientoEvento {
    long insertarEvento(DTEvento evento) throws ExcepcionPersonalizada;
    ArrayList<DTEvento> listaEvento() throws ExcepcionPersonalizada;
}
