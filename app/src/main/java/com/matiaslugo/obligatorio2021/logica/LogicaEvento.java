package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

class LogicaEvento {
     private static LogicaEvento instancia;

     public static LogicaEvento getInstancia() {
         if (instancia == null) {
             instancia = new LogicaEvento();
         }
         return instancia;
     }

     private LogicaEvento(){

     }

     public void validarEvento(DTEvento evento) throws ExcepcionPersonalizada {
         if (evento == null){
             throw new ExcepcionLogica();
         }
     }
}
