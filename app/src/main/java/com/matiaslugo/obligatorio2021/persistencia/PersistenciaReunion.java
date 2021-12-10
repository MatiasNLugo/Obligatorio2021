package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

import java.util.ArrayList;

public class PersistenciaReunion implements IPeristenciaReunion{
    private static PersistenciaReunion instancia;

    public static PersistenciaReunion getInstance(Context contexto){
        if(instancia == null){
            instancia = new PersistenciaReunion(contexto);
        }
        return  instancia;
    }


    private Context context;
    public static int id = 3;

    public PersistenciaReunion(@Nullable Context context) {
        this.context = context;
    }

    public ArrayList<DTReunion> listaReuniones(int idEvento) throws ExcepcionPersistencia {
        DataBaseHelper dbHelper ;
        SQLiteDatabase db ;
        ArrayList<DTReunion> reuniones = new ArrayList<>();
        DTReunion unaDTReunion = null;
        Cursor cursor = null;
        try {
            dbHelper = new DataBaseHelper(context);
            db  = dbHelper.getReadableDatabase();

            //cursor = db.rawQuery("SELECT * FROM " + DB.TABLA_REUNIONES + " WHERE idEvento = " + idEvento + ";",
              //      null);
            cursor = db.query(DB.TABLA_REUNIONES,DB.Reuniones.COLUMNAS,DB.Reuniones.IDEVENTO +
                    " = ? ", new String[] { String.valueOf(idEvento) }, null,null,DB.Reuniones._ID + " DESC", null );
            while (cursor.moveToNext()){
                    unaDTReunion = new DTReunion();
                    unaDTReunion =  instanciarReunion(cursor);

                    reuniones.add(unaDTReunion);
            }

            cursor.close();


            return reuniones;

        }catch (Exception ex ){
            throw  new ExcepcionPersistencia( "No se pudo listar las reuniones.");

        } finally {
            {
               if (cursor != null){
                   cursor.close();
               }
              // if(db != null) db.close();
               //if (dbHelper != null) dbHelper.close();
            }
        }
    }

    public ArrayList<DTReunion> listaReuniones() throws ExcepcionPersistencia {
        DataBaseHelper dbHelper ;
        SQLiteDatabase db ;
        ArrayList<DTReunion> reuniones = new ArrayList<>();
        DTReunion unaDTReunion = null;
        Cursor cursor = null;
        try {
            dbHelper = new DataBaseHelper(context);
            db  = dbHelper.getReadableDatabase();

            //cursor = db.rawQuery("SELECT * FROM " + DB.TABLA_REUNIONES + " WHERE idEvento = " + idEvento + ";",
            //      null);
            cursor = db.query(DB.TABLA_REUNIONES,DB.Reuniones.COLUMNAS,null, null, null,null,DB.Reuniones._ID + " DESC", null );
            while (cursor.moveToNext()){
                unaDTReunion = new DTReunion();
                unaDTReunion =  instanciarReunion(cursor);

                reuniones.add(unaDTReunion);
            }

            cursor.close();


            return reuniones;

        }catch (Exception ex ){
            throw  new ExcepcionPersistencia( "No se pudo listar las reuniones.");

        } finally {
            {
                if (cursor != null){
                    cursor.close();
                }
                // if(db != null) db.close();
                //if (dbHelper != null) dbHelper.close();
            }
        }
    }

    public long insertarReunion(DTReunion DTReunion){
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;
        try {

            int notificar = 0;
            if(DTReunion.isNotificar() ){
                notificar = 1;
            }
            //values.put("idEvento", null);
            values.put(DB.Reuniones._ID,id);
            values.put(DB.Reuniones.FECHA, DTReunion.getFecha());
            values.put(DB.Reuniones.HORA, DTReunion.getHora());
            values.put(DB.Reuniones.DESCRIPCION, DTReunion.getDescripcion());
            values.put(DB.Reuniones.LUGAR, DTReunion.getLugar());
            values.put(DB.Reuniones.IDEVENTO, DTReunion.getEvento().getIdEvento());
            values.put(DB.Reuniones.NOTIFICAR_CLIENTE,notificar);
            values.put(DB.Reuniones.OBJETIVO, DTReunion.getObjetivo());

            res = db.insert(DB.TABLA_REUNIONES, null, values);
            if(res > 1 ){
                id += 1;
            }
        } catch (Exception ex){
            ex.toString();
        } finally {

            db.close();
            return res;
        }
    }

    public Boolean modificarReunion(DTReunion DTReunion) {

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Integer notificar =0;
        notificar = DTReunion.isNotificar() ? 1 : 0;

        boolean correcto = false;
        try{

            db.execSQL("UPDATE " + DB.TABLA_REUNIONES + " SET Descripcion = '"+ DTReunion.getDescripcion()+
                    "',Objetivo = '"+ DTReunion.getObjetivo()+
                    "',Fecha = '"+ DTReunion.getFecha()+"',Hora = '"+ DTReunion.getHora()+"',Lugar = '"+ DTReunion.getLugar()+
                    "', NotificarCliente = '"+notificar+"' WHERE _ID = "+ DTReunion.getIdReunion());
            correcto = true;
            db.close();


        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();

        }

        return correcto;
    }

    public DTReunion instanciarReunion (Cursor cursor){
        int columnaId = cursor.getColumnIndex(DB.Reuniones._ID);
        int columnaIdEvento = cursor.getColumnIndex(DB.Reuniones.IDEVENTO);
        int columnaDescripcion = cursor.getColumnIndex(DB.Reuniones.DESCRIPCION);
        int columnaFecha = cursor.getColumnIndex(DB.Reuniones.FECHA);
        int columnaHora = cursor.getColumnIndex(DB.Reuniones.HORA);
        int columnaLugar = cursor.getColumnIndex(DB.Reuniones.LUGAR);
        int columnaObjetivo = cursor.getColumnIndex(DB.Reuniones.OBJETIVO);
        int columnaAvisarCliente = cursor.getColumnIndex(DB.Reuniones.NOTIFICAR_CLIENTE);

        boolean notificar = (cursor.getInt(columnaAvisarCliente)==1) ?true : false;

        DTEvento evento = new DTEvento();
        evento.setIdEvento(cursor.getInt(columnaIdEvento));
        DTReunion reunion = new DTReunion(cursor.getInt(columnaId),cursor.getString(columnaDescripcion),
                cursor.getString(columnaObjetivo),cursor.getString(columnaFecha),cursor.getString(columnaHora),cursor.getString(columnaLugar),notificar,evento);

        return reunion;


    }
}
