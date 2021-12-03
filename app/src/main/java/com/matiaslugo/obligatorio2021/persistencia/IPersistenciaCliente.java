package com.matiaslugo.obligatorio2021.persistencia;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.security.spec.ECField;
import java.util.ArrayList;

public interface IPersistenciaCliente {

    ArrayList<DTCliente> listaClientes() throws ExcepcionPersonalizada;
    long insertarCliente(DTCliente cliente) throws ExcepcionPersonalizada;
    boolean modificarCliente(DTCliente cliente) throws ExcepcionPersonalizada;
    boolean eliminarCliente(int idCliente) throws ExcepcionPersonalizada;
    boolean verificarDependenciaCliente(int idCliente) throws ExcepcionPersonalizada;
    DTCliente buscarCliente(int id) throws ExcepcionPersonalizada;
}
