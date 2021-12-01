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
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;

import java.util.ArrayList;

public class DbReuniones extends DataBaseHelper{
    private Context context;
    public static int id = 3;

    public DbReuniones(@Nullable Context context) {

        super(context);
        this.context = context;
    }

    public ArrayList<Reunion> listaReuniones(int idEvento) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Reunion> reuniones = new ArrayList<>();
        Reunion unaReunion = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM "+ DB.TABLA_REUNIONES+" WHERE idEvento = "+idEvento+";",
                null);
        //cursor = db.query(DB.TABLA_CLIENTES,DB.Clientes.COLUMNAS,);
        if(cursor.moveToFirst()){
            do{
                unaReunion = new Reunion();
                unaReunion.setIdReunion((cursor.getInt(0)));
                unaReunion.setFecha(cursor.getString(3));
                unaReunion.setHora(cursor.getString(4));
                unaReunion.setObjetivo(cursor.getString(2));
                unaReunion.setDescripcion(cursor.getString(1));
                unaReunion.setLugar(cursor.getString(5));
                unaReunion.setIdEvento(cursor.getInt(7));
                switch (cursor.getInt(6)){
                    case 0:
                        unaReunion.setNotificar(false);
                        break;
                    case 1:
                        unaReunion.setNotificar(true);
                        break;
                }
                reuniones.add(unaReunion);
            } while (cursor.moveToNext());
        }
        cursor.close();



        return reuniones;
    }

    public long insertarReunion(Reunion reunion){
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;
        try {

            int notificar = 0;
            if(reunion.isNotificar() ){
                notificar = 1;
            }
            //values.put("idEvento", null);
            values.put(DB.Reuniones._ID,id);
            values.put(DB.Reuniones.FECHA,reunion.getFecha());
            values.put(DB.Reuniones.HORA,reunion.getHora());
            values.put(DB.Reuniones.DESCRIPCION,reunion.getDescripcion());
            values.put(DB.Reuniones.LUGAR,reunion.getLugar());
            values.put(DB.Reuniones.IDEVENTO,reunion.getIdEvento());
            values.put(DB.Reuniones.NOTIFICAR_CLIENTE,notificar);
            values.put(DB.Reuniones.OBJETIVO,reunion.getObjetivo());

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

    public Boolean modificarReunion(Reunion reunion) {

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Integer notificar =0;
        notificar = reunion.isNotificar() ? 1 : 0;

        boolean correcto = false;
        try{

            db.execSQL("UPDATE " + DB.TABLA_REUNIONES + " SET Descripcion = '"+reunion.getDescripcion()+
                    "',Objetivo = '"+reunion.getObjetivo()+
                    "',Fecha = '"+reunion.getFecha()+"',Hora = '"+reunion.getHora()+"',Lugar = '"+reunion.getLugar()+
                    "', NotificarCliente = '"+notificar+"' WHERE _ID = "+reunion.getIdReunion());
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
}
