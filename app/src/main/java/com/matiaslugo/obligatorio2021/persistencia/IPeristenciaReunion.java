package com.matiaslugo.obligatorio2021.persistencia;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IPeristenciaReunion {
    ArrayList<DTReunion> listaReuniones(int idEvento) throws ExcepcionPersonalizada;
    ArrayList<DTReunion> listaReuniones() throws ExcepcionPersonalizada;
    long insertarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada;
    Boolean modificarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada;

}
