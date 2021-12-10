package com.matiaslugo.obligatorio2021.persistencia;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IPersistenciaEvento {

    long insertarEvento(DTEvento evento) throws ExcepcionPersonalizada;
    ArrayList<DTEvento> listaEvento() throws ExcepcionPersonalizada;
    long modificarEvento(DTEvento evento) throws ExcepcionPersistencia;
    long eliminarEvento(int idEvento) throws ExcepcionPersistencia;

    boolean verificarDependencia(int idCliente) throws ExcepcionPersistencia;
}
