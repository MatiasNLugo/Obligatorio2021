package com.matiaslugo.obligatorio2021.logica;

import android.content.Context;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionLogica;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class LogicaReunion {
    private static LogicaReunion instancia;

    public static LogicaReunion getInstancia() {
        if (instancia == null){
            instancia = new LogicaReunion();
        }
        return instancia;
    }

    private LogicaReunion(){

    }

    public void validarReunion(DTReunion reunion) throws ExcepcionPersonalizada {

        try {
            if (reunion == null) {
                throw new ExcepcionLogica("La Reunion no puede ser nula.");
            }

            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(reunion.getFecha());

            fecha.setHours(Integer.parseInt(reunion.getHora().substring(0, 2)));
            fecha.setMinutes(Integer.parseInt(reunion.getHora().substring(3)));
            Date temp = Calendar.getInstance().getTime();

            if (fecha.before(temp)) {
                throw new ExcepcionLogica("La fecha y hora de la reunion no pueden ser anterior a la fecha y hora actual.");
            }

            if (reunion.getEvento() == null) {
                throw new ExcepcionLogica("Error al verificar el Evento.");
            }
            Date fechaEvento = new SimpleDateFormat("dd/MM/yyyy").parse(reunion.getEvento().getFecha());
            fechaEvento.setHours(Integer.parseInt(reunion.getEvento().getHora().substring(0, 2)));
            fechaEvento.setMinutes(Integer.parseInt(reunion.getEvento().getHora().substring(3)));
            if (fecha.after(fechaEvento)) {
                throw new ExcepcionLogica("La fecha de la Reunión no puede ser mayor a " +
                        reunion.getEvento().getFecha() + " - " +
                        reunion.getEvento().getHora() + " Hs.");

            }

            if (reunion.getLugar().isEmpty() || reunion.getLugar() == null) {
                throw new ExcepcionLogica("El Lugar de la reunion no es valido.");
            }

            if (reunion.getObjetivo().isEmpty() || reunion.getObjetivo() == null) {
                throw new ExcepcionLogica("El Objetivo de la reunion no es valido.");
            }
            if (reunion.getDescripcion().isEmpty() || reunion.getDescripcion() == null) {
                throw new ExcepcionLogica("La Descipción de la reunion no es valida.");
            }

        } catch (ParseException e) {
            throw new ExcepcionLogica("Error al validar la fecha de la Reunion.");
        }

    }

    public boolean validarReunionesSolapadas(DTReunion reunion, ArrayList<DTReunion> reuniones ) throws ExcepcionLogica {

        boolean solapadas = false;
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(reunion.getFecha());
            fecha.setHours(Integer.parseInt(reunion.getHora().substring(0,2)));
            fecha.setMinutes(Integer.parseInt(reunion.getHora().substring(3)));

            Date fechaReunion;

            for (DTReunion item : reuniones) {
                fechaReunion = new SimpleDateFormat("dd/MM/yyyy").parse(item.getFecha());
                fechaReunion.setHours(Integer.parseInt(item.getHora().substring(0,2)));
                fechaReunion.setMinutes(Integer.parseInt(item.getHora().substring(3)));
                if(fecha.equals(fechaReunion)){
                    solapadas = true;
                }
            }

            return solapadas;
        } catch(Exception e){
            throw new ExcepcionLogica("Error al verificar reuniones solapadas.");
        }

    }

    public ArrayList<DTReunion> listarReunionesPendientes(ArrayList<DTReunion> reuniones) throws ExcepcionLogica {
        if(reuniones == null){
            throw new ExcepcionLogica("La lista de reuniones no puede ser nula.");
        }
        Date fecha;
        Date temp = Calendar.getInstance().getTime();
        DTReunion reunion;
        ArrayList<DTReunion> pendientes = new ArrayList<>();
        try {

            for (DTReunion item : reuniones){
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(item.getFecha());
                    fecha.setHours(Integer.parseInt(item.getHora().substring(0,2)));
                    fecha.setMinutes(Integer.parseInt(item.getHora().substring(3)));
                    if (fecha.after(temp)){
                        pendientes.add(item);
                    }

            }

            return pendientes;


        } catch (Exception ex) {
            throw new ExcepcionLogica("Error al intentar mostrar las reuniones pendientes");
        }

    }



}
