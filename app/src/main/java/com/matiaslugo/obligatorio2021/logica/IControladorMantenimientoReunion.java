package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;
import java.util.List;

public interface IControladorMantenimientoReunion {
    ArrayList<DTReunion> listaReuniones(int idEvento) throws ExcepcionPersonalizada;
    long insertarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada;
    Boolean modificarReunion(DTReunion DTReunion) throws ExcepcionPersonalizada;
    ArrayList<DTReunion> listarPendientes(ArrayList<DTReunion> reuniones)  throws ExcepcionPersonalizada;
}
