package com.matiaslugo.obligatorio2021.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Gasto;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;

import java.util.ArrayList;

public class DbGastos extends DataBaseHelper{

    private Context context;
    public static int id = 3;

    public DbGastos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Gasto> listaGastos(int idEvento) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Gasto> gastos = new ArrayList<>();
        Gasto unGasto = null;
        Cursor cursor = null;
        Evento unEvento = new Evento();

        cursor = db.rawQuery("SELECT * FROM "+ DB.TABLA_GASTOS+" WHERE idEvento = "+idEvento+";",
                null);
        //cursor = db.query(DB.TABLA_CLIENTES,DB.Clientes.COLUMNAS,);
        if(cursor.moveToFirst()){
            do{
                unGasto = new Gasto();
                unGasto.setIdGasto((cursor.getInt(0)));
                unGasto.setMotivo(cursor.getString(1));
                unGasto.setProveedor(cursor.getString(2));
                unGasto.setMonto(cursor.getFloat(3));
                unEvento.setIdEvento(cursor.getInt(4));
                unGasto.setUnEvento(unEvento);
                gastos.add(unGasto);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return gastos;

}

}
