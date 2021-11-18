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
            values.put("fecha",evento.getFecha());
            values.put("hora", evento.getHora());
            values.put("duracion", evento.getDuracion());
            values.put("titulo", evento.getTitulo());
            values.put("tipo", evento.getTipo());
            values.put("cantAsistentes", evento.getCantAsistentes());
            values.put("idCliente", evento.getIdCliente());

            res = db.insert(TABLA_EVENTOS, null, values);


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

        cursor = db.rawQuery("SELECT * FROM eventos;", null);
        if(cursor.moveToFirst()){
            do{
                unEvento = new Evento();
                unEvento.setIdEvento(cursor.getInt(0));
                unEvento.setFecha(cursor.getString(1));
                unEvento.setHora(cursor.getString(2));
                unEvento.setDuracion(cursor.getInt(3));
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
