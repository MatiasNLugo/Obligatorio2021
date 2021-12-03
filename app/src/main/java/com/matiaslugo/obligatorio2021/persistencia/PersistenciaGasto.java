package com.matiaslugo.obligatorio2021.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;

import java.util.ArrayList;

public class PersistenciaGasto implements  IPersistenciaGasto{

    public static  PersistenciaGasto instance;

    public static PersistenciaGasto getInstance(Context contexto){
        if(instance == null){
            instance = new PersistenciaGasto(contexto);
        }

        return instance;
    }

    private Context context;
    public static int id = 3;

    public PersistenciaGasto(@Nullable Context context) {
        this.context = context;
    }

    public ArrayList<DTGasto> listaGastos(int idEvento) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<DTGasto> DTGastos = new ArrayList<>();
        DTGasto unDTGasto = null;
        Cursor cursor = null;
        DTEvento unEvento = new DTEvento();

        cursor = db.rawQuery("SELECT * FROM "+ DB.TABLA_GASTOS+" WHERE idEvento = "+idEvento+";",
                null);
        //cursor = db.query(DB.TABLA_CLIENTES,DB.Clientes.COLUMNAS,);
        if(cursor.moveToFirst()){
            do{
                unDTGasto = new DTGasto();
                unDTGasto.setIdGasto((cursor.getInt(0)));
                unDTGasto.setMotivo(cursor.getString(1));
                unDTGasto.setProveedor(cursor.getString(2));
                unDTGasto.setMonto(cursor.getFloat(3));
                unEvento.setIdEvento(cursor.getInt(4));
                unDTGasto.setUnEvento(unEvento);
                DTGastos.add(unDTGasto);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return DTGastos;

}

}
