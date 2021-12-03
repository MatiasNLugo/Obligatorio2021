package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

class LogicaCliente {
     private static LogicaCliente instancia;
       public static LogicaCliente getInstancia() {
         if (instancia == null) {
             instancia = new LogicaCliente();
         }
         return instancia;
     }

     private LogicaCliente(){

     }

     public void validarCliente(DTCliente cliente) throws ExcepcionPersonalizada {
           if(cliente == null){
               throw  new ExcepcionLogica();
           }
     }


}
