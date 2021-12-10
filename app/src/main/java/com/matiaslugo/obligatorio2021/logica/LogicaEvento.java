package com.matiaslugo.obligatorio2021.logica;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("deprecation")
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

            if (evento == null) {
                throw new ExcepcionLogica("El evento no puede ser nulo.");
            }
            if (evento.getCantAsistentes() < 0){
                throw new ExcepcionLogica("La cantidad de asistentes no puede ser menor a cero.");
            }
            if( evento.getTipo() < 1  || evento.getTipo() > 5 ){
                throw new ExcepcionLogica("El tipo de evento no es valido.");
            }
            if(evento.getDuracion() == null || evento.getDuracion().isEmpty()){
                throw new ExcepcionLogica("La duración no puede estar vacia.");
            } else if(Integer.parseInt(evento.getDuracion()) < 0){
                throw new ExcepcionLogica("La duración no puede ser menor a cero.");
            }
            if(evento.getTitulo().isEmpty() || evento.getTitulo() == null){
                throw new ExcepcionLogica("El Título no puede estar vacío.");
            }
            try {
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(evento.getFecha());
                fecha.setHours(Integer.parseInt(evento.getHora().substring(0, 2)));
                fecha.setMinutes(Integer.parseInt(evento.getHora().substring(3)));

                Date fechaActual = Calendar.getInstance().getTime();
                if (fecha.before(fechaActual)) {
                    throw new ExcepcionLogica("La fecha del Evento no puede ser anterior a la fecha actual.");
                }

            } catch (ParseException e) {
                throw new ExcepcionLogica("Error al convertir la fecha del Evento.");
            }
     }






}
