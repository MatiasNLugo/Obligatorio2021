package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

import java.util.ArrayList;

public class PersistenciaEvento implements IPersistenciaEvento{


    private static PersistenciaEvento instancia;

    public static PersistenciaEvento getInstance(Context contexto){
        if (instancia == null){
            instancia = new PersistenciaEvento(contexto);
        }

        return instancia;
    }

    private Context context;

    public PersistenciaEvento(@Nullable Context context) {
        this.context = context;
    }

    public long insertarEvento(DTEvento evento){
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;
        try {

            //values.put("idEvento", null);
            values.put(DB.Eventos.FECHA,evento.getFecha());
            values.put(DB.Eventos.HORA, evento.getHora());
            values.put(DB.Eventos.DURACION, evento.getDuracion());
            values.put(DB.Eventos.TITULO, evento.getTitulo());
            values.put(DB.Eventos.TIPO, evento.getTipo());
            values.put(DB.Eventos.ASISTENTES, evento.getCantAsistentes());
            values.put(DB.Eventos.IDCLIENTE, evento.getIdCliente());

            res = db.insert(DB.TABLA_EVENTOS, null, values);


        } catch (Exception ex){
            ex.toString();
        } finally {

            db.close();
            return res;
        }
    }

    public ArrayList<DTEvento> listaEvento(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<DTEvento> eventos = new ArrayList<>();
        DTEvento unEvento = null;
        Cursor cursor = null;

        cursor = db.query(DB.TABLA_EVENTOS, DB.Eventos.COLUMNAS,null,null,null,null,DB.Eventos._ID+" DESC");
        if(cursor.moveToFirst()){
            do{
                unEvento = new DTEvento();
                unEvento.setIdEvento(cursor.getInt(0));
                unEvento.setFecha(cursor.getString(1));
                unEvento.setHora(cursor.getString(2));
                unEvento.setDuracion(cursor.getString(3));
                unEvento.setTitulo(cursor.getString(4));
                unEvento.setTipo(cursor.getInt(5));
                unEvento.setCantAsistentes(cursor.getInt(6));
                unEvento.setIdCliente(cursor.getInt(7));
                eventos.add(unEvento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return eventos;
    }

    public long modificarEvento(DTEvento evento) throws ExcepcionPersistencia {
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{
            values.put(DB.Eventos.TITULO,evento.getTitulo());
            values.put(DB.Eventos.FECHA,evento.getFecha());
            values.put(DB.Eventos.HORA,evento.getHora());
            values.put(DB.Eventos.ASISTENTES,evento.getCantAsistentes());
            values.put(DB.Eventos.DURACION,evento.getDuracion());
            values.put(DB.Eventos.TIPO,evento.getTipo());
            values.put(DB.Eventos.IDCLIENTE,evento.getIdCliente());

            res = db.update(DB.TABLA_EVENTOS, values, DB.Eventos._ID + " = ? ", new String[]{String.valueOf(evento.getIdEvento())} );

            return res;
        } catch (Exception ex){
            throw  new ExcepcionPersistencia("No se pudo crear el Gasto.");
        } finally {
            db.close();
            dbHelper.close();
        }
    }

    public long eliminarEvento(int idEvento) throws ExcepcionPersistencia {
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            res = db.delete(DB.TABLA_TAREAS, DB.Tareas.IDEVENTO + " = ? ", new String[]{String.valueOf(idEvento)});
            res += db.delete(DB.TABLA_GASTOS, DB.Gastos.IDEVENTO + " = ? ", new String[]{String.valueOf(idEvento)});
            res += db.delete(DB.TABLA_REUNIONES, DB.Reuniones.IDEVENTO + " = ? ", new String[]{String.valueOf(idEvento)});
            res += db.delete(DB.TABLA_EVENTOS, DB.Eventos._ID + " = ? ", new String[]{String.valueOf(idEvento)});

            db.setTransactionSuccessful();
            return  res;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar el evento");
        } finally {
            db.endTransaction();
            db.close();
            dbHelper.close();
        }
    }

    @Override
    public boolean verificarDependencia(int idCliente) throws ExcepcionPersistencia {
        Boolean verificar = false;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        try{
            cursor = db.query(DB.TABLA_EVENTOS,DB.Eventos.COLUMNAS,DB.Eventos.IDCLIENTE + " = ? ", new String[] {String.valueOf(idCliente)},null,null,null);
            if(cursor.moveToNext()){
                verificar = true;
            }
        } catch (Exception ex ){
            throw new ExcepcionPersistencia("No se pudo verificar la dependencia");

        } finally {
            db.close();
            dbHelper.close();
            return verificar;
        }
    }
}
