package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;

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
}
