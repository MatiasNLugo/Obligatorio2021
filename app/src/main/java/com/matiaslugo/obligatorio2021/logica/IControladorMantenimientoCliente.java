package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;

public interface IControladorMantenimientoCliente {
    long insertarCliente(DTCliente cliente) throws ExcepcionPersonalizada;
    ArrayList<DTCliente> listaClientes() throws ExcepcionPersonalizada;
    boolean modificarCliente(DTCliente unCliente) throws  ExcepcionPersonalizada;
    boolean eliminarCliente(int idCliente) throws  ExcepcionPersonalizada;
    boolean verificarDependenciaCliente(int idCliente) throws  ExcepcionPersonalizada;
    DTCliente buscarCliente(int id) throws  ExcepcionPersonalizada;
}
