package com.matiaslugo.obligatorio2021.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;

import java.util.ArrayList;

public class DbEventos extends DataBaseHelper{
    private Context context;
    public DbEventos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEvento(Evento evento){
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



    public ArrayList<Evento> listaEvento(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento unEvento = null;
        Cursor cursor = null;

        cursor = db.query(DB.TABLA_EVENTOS, DB.Eventos.COLUMNAS,null,null,null,null,DB.Eventos._ID+" DESC");
        if(cursor.moveToFirst()){
            do{
                unEvento = new Evento();
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
