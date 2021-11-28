package com.matiaslugo.obligatorio2021.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;

import java.util.ArrayList;

public class DbReuniones extends DataBaseHelper{
    Context context;

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
}
