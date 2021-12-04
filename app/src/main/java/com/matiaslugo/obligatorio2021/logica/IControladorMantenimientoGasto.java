package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IControladorMantenimientoGasto {
    ArrayList<DTGasto> listaGastos(int idEvento) throws ExcepcionPersonalizada;
    long insertarGasto(DTGasto gasto) throws ExcepcionPersistencia;

}
