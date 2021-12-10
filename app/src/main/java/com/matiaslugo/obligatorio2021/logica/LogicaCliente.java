package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.text.ParseException;

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

               if (cliente == null) {
                   throw new ExcepcionLogica("El cliente no puede er nulo.");
               }

               if (cliente.getIdCliente()<0){
                   throw new ExcepcionLogica("Error Id de Cliente Negativo.");
               }
                int telefono = Integer.parseInt(cliente.getTelefono());
               if (!(cliente.getTelefono().matches("[+-]?\\d*(\\.\\d+)?"))){
                   throw new ExcepcionLogica("El teléfono no es correcto.");
               }

               if ( cliente instanceof DTParticular ){
                   if (((DTParticular) cliente).getNombre().isEmpty())
                       throw new ExcepcionLogica("Debe ingresar Nombre del Cliente.");
                   if(((DTParticular) cliente).getCedula().isEmpty())
                       throw new ExcepcionLogica("Debe ingresar un número de Cédula.");
                   try {
                       int cedula = Integer.parseInt(((DTParticular) cliente).getCedula());
                   } catch (Exception ex){
                       throw new ExcepcionLogica("Error en el formato de la Cédula.");
                   }
               }
         if ( cliente instanceof DTComercial){
             if (((DTComercial) cliente).getRazonSocial().isEmpty())
                 throw new ExcepcionLogica("Debe ingresar Razón Social del Cliente.");
             if(((DTComercial) cliente).getRut().isEmpty())
                 throw new ExcepcionLogica("Debe ingresar un número de Rut.");
             try {
                 int cedula = Integer.parseInt(((DTComercial) cliente).getRut());
             } catch (Exception ex){
                 throw new ExcepcionLogica("Error en el formato del Rut.");
             }
         }

         if (!(cliente.getTelefono().matches("[+-]?\\d*(\\.\\d+)?")) || cliente.getTelefono() == null){
             throw new ExcepcionLogica("El Teléfono no es correcto.");
         }

         if(cliente.getCorreo() == null || !cliente.getCorreo().matches("^[\\w-+]+(\\.[\\w-]{1,62}){0,126}@[\\w-]{1,63}(\\.[\\w-]{1,62})+/[\\w-]+$"))
         {
             throw  new ExcepcionLogica("El Correo no es correcto.");
         }

         if (cliente.getDireccion().isEmpty() || cliente.getDireccion() == null){
             throw new ExcepcionLogica("La Dirección del Cliente no es correcta.");
         }


     }

}
