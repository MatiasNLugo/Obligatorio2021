package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

import java.util.ArrayList;

public class PersistenciaTarea implements IPeristenciaTarea{

    public static PersistenciaTarea instance;

    public static PersistenciaTarea getInstance(Context context){
        if (instance == null){
            instance = new PersistenciaTarea(context);
        }
        return instance;
    }

    private Context context;
    public static int id = 4;

    public PersistenciaTarea(@Nullable Context context) {
        this.context = context;
    }
    public ArrayList<DTTarea> listaTareas(int idEvento) throws ExcepcionPersistencia {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<DTTarea> tareas = new ArrayList<>();
        DTTarea unaTarea = null;
        Cursor cursor = null;

        try {
            cursor = db.query(DB.TABLA_TAREAS,DB.Tareas.COLUMNAS,DB.Tareas.IDEVENTO +
                    " = ? ",new String[] {String.valueOf(idEvento)},null,null,DB.Tareas._ID + " ASC",null);
            //cursor = db.rawQuery("Select * from Gastos", null);
            while(cursor.moveToNext()){
                unaTarea = new DTTarea();
                unaTarea = instanciarTarea(cursor);
                tareas.add(unaTarea);
            }
            cursor.close();
            return tareas;
        }catch (Exception ex){
            throw new ExcepcionPersistencia("Error al listar las Tareas");
        }
    }

    public long cambiarEstadoTarea(DTTarea tarea) throws ExcepcionPersistencia {
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{
            values.put(DB.Tareas._ID,tarea.getIdTarea());

            int realizada = (tarea.isRealizada()) ? 1 : 0;
            values.put(DB.Tareas.REALIZADA, realizada);

            res = db.update(DB.TABLA_TAREAS, values, DB.Tareas._ID + " = ? ", new String[]{String.valueOf(tarea.getIdTarea())} );


            return res;
        } catch (Exception ex){
            throw  new ExcepcionPersistencia("No se pudo crear el Gasto.");
        } finally {
            db.close();
            dbHelper.close();
        }

    }

    public long insertarTarea(DTTarea tarea ) throws ExcepcionPersistencia {
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{
            values.put(DB.Tareas._ID,id);
            values.put(DB.Tareas.IDEVENTO,tarea.getUnEvento().getIdEvento());
            values.put(DB.Tareas.DESCIPCION,tarea.getDescipcion());
            values.put(DB.Tareas.FECHALIMITE, tarea.getFechaLimite());

            int realizada = (tarea.isRealizada()) ? 1 : 0;
            values.put(DB.Tareas.REALIZADA, realizada);

            res = db.insert(DB.TABLA_TAREAS,null,values);

            if(res > 0) {
                id += 1;
            }
            return res;
        } catch (Exception ex){
            throw  new ExcepcionPersistencia("No se pudo crear el Gasto.");
        } finally {
            db.close();
            dbHelper.close();
        }

    }

    public DTTarea instanciarTarea(Cursor cursor){
        int columnaId = cursor.getColumnIndex(DB.Tareas._ID);
        int columnaDescripcion = cursor.getColumnIndex(DB.Tareas.DESCIPCION);
        int columnaFecha = cursor.getColumnIndex(DB.Tareas.FECHALIMITE);
        int columnaIdEvento = cursor.getColumnIndex(DB.Tareas.IDEVENTO);
        int columanRealizada = cursor.getColumnIndex(DB.Tareas.REALIZADA);

        Boolean realizada = ((int)cursor.getInt(columanRealizada) == 1) ? true : false;

        DTEvento evento = new DTEvento();
        evento.setIdEvento(cursor.getInt(columnaIdEvento));
        DTTarea tarea = new DTTarea(cursor.getInt(columnaId),cursor.getString(columnaDescripcion),cursor.getString(columnaFecha),realizada,evento);

        return tarea;
    }


}
