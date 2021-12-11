package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

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

    public PersistenciaGasto(@Nullable Context context) {
        this.context = context.getApplicationContext();
    }

    public ArrayList<DTGasto> listaGastos(int idEvento) throws ExcepcionPersistencia {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<DTGasto> DTGastos = new ArrayList<>();
        DTGasto unDTGasto = null;
        Cursor cursor = null;
        DTGasto gasto ;

        try {
            cursor = db.query(DB.TABLA_GASTOS,DB.Gastos.COLUMNAS,DB.Gastos.IDEVENTO +
                    " = ? ",new String[] {String.valueOf(idEvento)},null,null,DB.Gastos._ID + " DESC",null);
            //cursor = db.rawQuery("Select * from Gastos", null);
            while(cursor.moveToNext()){
                gasto = new DTGasto();
                gasto = instanciarGasto(cursor);
                DTGastos.add(gasto);
            }
            cursor.close();
            return DTGastos;
        }catch (Exception ex){
            throw new ExcepcionPersistencia("Error al listar los gastos");
        }
}

    public long insertarGasto(DTGasto gasto) throws ExcepcionPersistencia {
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{

            values.put(DB.Gastos.IDEVENTO,gasto.getUnEvento().getIdEvento());
            values.put(DB.Gastos.MONTO,gasto.getMonto());
            values.put(DB.Gastos.MOTIVO, gasto.getMotivo());
            values.put(DB.Gastos.PROVEEDOR, gasto.getProveedor());

            res = db.insert(DB.TABLA_GASTOS,null,values);

            return res;
        } catch (Exception ex){
            throw  new ExcepcionPersistencia("No se pudo crear el Gasto.");
        } finally {
            db.close();
            dbHelper.close();
        }

    }

    public DTGasto instanciarGasto(Cursor cursor){
        int columnaId = cursor.getColumnIndex(DB.Gastos._ID);
        int columnaMotivo = cursor.getColumnIndex(DB.Gastos.MOTIVO);
        int columnaProveedor = cursor.getColumnIndex(DB.Gastos.PROVEEDOR);
        int columnaIdEvento = cursor.getColumnIndex(DB.Gastos.IDEVENTO);
        int columnaMonto = cursor.getColumnIndex(DB.Gastos.MONTO);
        DTEvento evento = new DTEvento();
        evento.setIdEvento(cursor.getInt(columnaIdEvento));
        DTGasto gasto = new DTGasto(cursor.getInt(columnaId),cursor.getString(columnaMotivo),cursor.getString(columnaProveedor),cursor.getFloat(columnaMonto),evento);

        return gasto;
    }
}
