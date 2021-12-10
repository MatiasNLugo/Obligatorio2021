package com.matiaslugo.obligatorio2021.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;

import java.util.ArrayList;

public class PersistenciaCliente extends DataBaseHelper implements IPersistenciaCliente{

    public static PersistenciaCliente instancia;

    public static  PersistenciaCliente getInstance(Context contexto){
        if ( instancia == null){
            instancia = new PersistenciaCliente(contexto);
        }
        return instancia;
    }

    private  Context context;
    public PersistenciaCliente(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarCliente(DTCliente cliente){
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;
        try {


            //values.put("idCliente", null);
            values.put(DB.Clientes.DIRECCION, cliente.getDireccion());
            values.put(DB.Clientes.TELEFONO, cliente.getTelefono());
            values.put(DB.Clientes.CORREO, cliente.getCorreo());

            ContentValues valuesTipo = new ContentValues();
            // IDENTIFICAR EL TIPO DE CLIENTE PARA ASIGNAR LOS DATOS NECESARIOS.
            if (cliente instanceof DTComercial) {
                valuesTipo.put("rut", ((DTComercial) cliente).getRut());
                valuesTipo.put("razonSocial",((DTComercial) cliente).getRazonSocial());

                db.beginTransaction();
                res = db.insert(DB.TABLA_CLIENTES, null, values);
                valuesTipo.put(DB.Comerciales._ID,res);
                res = db.insert(DB.TABLA_COMERCIALES, null, valuesTipo);


            } else if (cliente instanceof DTParticular) {
                valuesTipo.put("cedula", ((DTParticular) cliente).getCedula());
                valuesTipo.put("nombre", ((DTParticular) cliente).getNombre());

                db.beginTransaction();
                res = db.insert(DB.TABLA_CLIENTES, null, values);


                //cursor = db.rawQuery(" SELECT idCliente from clientes order by idCliente DESC limit 1", null);
                valuesTipo.put(DB.Particulares._ID,res);

                res = db.insert(DB.TABLA_PARTICULARES, null, valuesTipo);
                db.setTransactionSuccessful();
            }

        } catch (Exception ex){
            ex.toString();
        } finally {

            db.endTransaction();
            db.close();
        return res;
    }
    }

    public ArrayList<DTCliente> listaClientes(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<DTCliente> clientes = new ArrayList<>();
        DTCliente unCliente = null;
        DTCliente unClienteP = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM "+ DB.TABLA_CLIENTES+" c inner join Comerciales p on p._id = c._id;", null);
        //cursor = db.query(DB.TABLA_CLIENTES,DB.Clientes.COLUMNAS,);
        if(cursor.moveToFirst()){
            do{
                unCliente = new DTComercial();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((DTComercial)unCliente).setRut(cursor.getString(5));
                ((DTComercial)unCliente).setRazonSocial(cursor.getString(6));
                clientes.add(unCliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //cursor = db.rawQuery("SELECT c.*, co.*  FROM " + TABLA_CLIENTES + " c INNER JOIN " + TABLA_PARTICULARES + " co ON co.idCliente = c.idCliente;", null);
        cursor = db.rawQuery("SELECT * FROM Clientes c inner join Particulares p on p._id= c._id;", null);

        if(cursor.moveToFirst()){
            do{
                unClienteP = new DTParticular();
                unClienteP.setIdCliente(cursor.getInt(0));
                unClienteP.setDireccion(cursor.getString(1));
                unClienteP.setTelefono(cursor.getString(2));
                unClienteP.setCorreo(cursor.getString(3));
                ((DTParticular)unClienteP).setNombre(cursor.getString(5));
                ((DTParticular)unClienteP).setCedula(cursor.getString(6));
                clientes.add(unClienteP);
            } while (cursor.moveToNext());
        }
        cursor.close();


        return clientes;
    }

    public boolean modificarCliente(DTCliente unCliente){

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        boolean correcto = false;
        try{

            db.execSQL("UPDATE " + DB.TABLA_CLIENTES + " SET Direccion = '"+unCliente.getDireccion()+"',Telefono = '"+unCliente.getTelefono()+"',Correo = '"+unCliente.getCorreo()+"' WHERE _ID = "+unCliente.getIdCliente());
            correcto = true;
            db.close();
            if (unCliente instanceof DTComercial){
                db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE " + DB.TABLA_COMERCIALES + " SET Rut = '"+((DTComercial) unCliente).getRut()+"',RazonSocial = '"+((DTComercial) unCliente).getRazonSocial()+"' WHERE _ID = "+unCliente.getIdCliente());
                db.close();
                correcto = true;
            } else if (unCliente instanceof DTParticular){
                db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE " + DB.TABLA_PARTICULARES + " SET Nombre = '"+((DTParticular) unCliente).getNombre()+"',Cedula = '"+((DTParticular) unCliente).getCedula()+"' WHERE _ID = "+unCliente.getIdCliente());
                db.close();
                correcto = true;
            }


        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return  correcto;

    }

    public long eliminarCliente(int idCliente) throws ExcepcionPersistencia {

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long res =0;

            try {
                db.beginTransaction();
                res = db.delete(DB.TABLA_COMERCIALES, DB.Comerciales._ID + " = ? ", new String[]{String.valueOf(idCliente)});
                res += db.delete(DB.TABLA_PARTICULARES, DB.Particulares._ID + " = ? ", new String[]{String.valueOf(idCliente)});
                res += db.delete(DB.TABLA_CLIENTES, DB.Clientes._ID + " = ? ", new String[]{String.valueOf(idCliente)});

                db.setTransactionSuccessful();
                return  res;
            } catch (Exception ex) {
                throw new ExcepcionPersistencia("No se pudo eliminar el cliente.");
            } finally {
                db.endTransaction();
                db.close();
                dbHelper.close();
                return res;
            }

    }

    public boolean verificarDependenciaCliente(int idCliente){

        Boolean res = false;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = db.rawQuery("SELECT * FROM Eventos WHERE idCliente = " + idCliente + ";", null);
            if (cursor.moveToFirst()) {
                res = true;
            }
            cursor.close();
            return res;
        } catch (Exception ex){
            ex.toString();

    } finally {

            db.close();
            return res;
        }
    }

    public DTCliente buscarCliente(int id) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DTCliente unCliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Clientes c inner join Comerciales p on p._ID = c._ID WHERE c._ID = "+ id + ";" ,null);
        if(cursor.moveToFirst()){

                unCliente = new DTComercial();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((DTComercial)unCliente).setRut(cursor.getString(5));
                ((DTComercial)unCliente).setRazonSocial(cursor.getString(6));
        }
        cursor.close();
        //cursor = db.rawQuery("SELECT c.*, co.*  FROM " + TABLA_CLIENTES + " c INNER JOIN " + TABLA_PARTICULARES + " co ON co.idCliente = c.idCliente;", null);
        cursor = db.rawQuery("SELECT * FROM Clientes c inner join Particulares p on p._ID = c._ID WHERE c._ID = "+ id + ";", null);

        if(cursor.moveToFirst()){
                unCliente = new DTParticular();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((DTParticular)unCliente).setNombre(cursor.getString(5));
                ((DTParticular)unCliente).setCedula(cursor.getString(6));

        }
        cursor.close();

        return unCliente;

    }
}
