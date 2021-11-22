package com.matiaslugo.obligatorio2021.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.Telephony;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;

import java.security.cert.Extension;
import java.util.ArrayList;

public class DbClientes extends DataBaseHelper{
    Context context;
    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;



    }


    // TODO VERIFICAR DATOS YA INGRESADOS NO FUNCIONA EL JOIN, USAR TRANSAC

    public long insertarCliente(Cliente cliente){
        long res = 0;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor;
        try {


            //values.put("idCliente", null);
            values.put("direccion", cliente.getDireccion());
            values.put("telefono", cliente.getTelefono());
            values.put("correo", cliente.getCorreo());

            ContentValues valuesTipo = new ContentValues();
            // IDENTIFICAR EL TIPO DE CLIENTE PARA ASIGNAR LOS DATOS NECESARIOS.
            if (cliente instanceof Comercial) {
                valuesTipo.put("rut", ((Comercial) cliente).getRut());
                valuesTipo.put("razonSocial",((Comercial) cliente).getRazonSocial());

                db.beginTransaction();
                res = db.insert(TABLA_CLIENTES, null, values);
                valuesTipo.put("idCliente",res);
                res = db.insert(TABLA_COMERCIALES, null, valuesTipo);


            } else if (cliente instanceof Particular) {
                valuesTipo.put("cedula", ((Particular) cliente).getCedula());
                valuesTipo.put("nombre", ((Particular) cliente).getNombre());

                db.beginTransaction();
                res = db.insert(TABLA_CLIENTES, null, values);


                //cursor = db.rawQuery(" SELECT idCliente from clientes order by idCliente DESC limit 1", null);
                valuesTipo.put("idCliente",res);

                res = db.insert(TABLA_PARTICULARES, null, valuesTipo);

            }

        } catch (Exception ex){
            ex.toString();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        return res;
    }
    }

    public ArrayList<Cliente> listaClientes(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente unCliente = null;
        Cliente unClienteP = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM clientes c inner join comerciales p on p.idCliente = c.idCliente;", null);
        if(cursor.moveToFirst()){
            do{
                unCliente = new Comercial();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((Comercial)unCliente).setRut(cursor.getString(5));
                ((Comercial)unCliente).setRazonSocial(cursor.getString(6));
                clientes.add(unCliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //cursor = db.rawQuery("SELECT c.*, co.*  FROM " + TABLA_CLIENTES + " c INNER JOIN " + TABLA_PARTICULARES + " co ON co.idCliente = c.idCliente;", null);
        cursor = db.rawQuery("SELECT * FROM clientes c inner join particulares p on p.idCliente = c.idCliente;", null);

        if(cursor.moveToFirst()){
            do{
                unClienteP = new Particular();
                unClienteP.setIdCliente(cursor.getInt(0));
                unClienteP.setDireccion(cursor.getString(1));
                unClienteP.setTelefono(cursor.getString(2));
                unClienteP.setCorreo(cursor.getString(3));
                ((Particular)unClienteP).setNombre(cursor.getString(5));
                ((Particular)unClienteP).setCedula(cursor.getString(6));
                clientes.add(unClienteP);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return clientes;
    }

    public boolean modificarCliente(Cliente unCliente){

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        boolean correcto = false;
        try{

            db.execSQL("UPDATE " + TABLA_CLIENTES + " SET direccion = '"+unCliente.getDireccion()+"',telefono = '"+unCliente.getTelefono()+"',correo = '"+unCliente.getCorreo()+"', WHERE idCliente = '"+unCliente.getIdCliente()+"'; " );
            correcto = true;
            db.close();
            if (unCliente instanceof Comercial){
                db.execSQL("UPDATE " + TABLA_COMERCIALES + " SET rut = '"+((Comercial) unCliente).getRut()+"',razonSocial = '"+((Comercial) unCliente).getRazonSocial()+"', WHERE idCliente = '"+unCliente.getIdCliente()+"'; " );
                correcto = true;
            } else if (unCliente instanceof Particular){
                db.execSQL("UPDATE " + TABLA_COMERCIALES + " SET nombre = '"+((Particular) unCliente).getNombre()+"',cedula = '"+((Particular) unCliente).getCedula()+"', WHERE idCliente = '"+unCliente.getIdCliente()+"'; " );
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

    public boolean eliminarCliente(int idCliente){

        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean correcto = false;
        boolean dependencia = false;
        Cursor cursor = null;

        try{

            cursor = db.rawQuery(" SELECT * FROM " +TABLA_EVENTOS+ " WHERE idCliente = '"+idCliente+"';",null);
            if(cursor.moveToNext()){
                cursor.close();
                db.close();
                dependencia = true;
            }
            if (dependencia = true){
                db.execSQL("UPDATE "+TABLA_CLIENTES+" SET activo=0;");
                db.close();
            } else {
                db.execSQL("DELETE FROM  " + TABLA_COMERCIALES + " WHERE idCliente = '"+idCliente+"'; " );
                db.close();
                db.execSQL("DELETE FROM  " + TABLA_PARTICULARES + " WHERE idCliente = '"+idCliente+"';" );
                db.close();
                db.execSQL("DELETE FROM  " + TABLA_CLIENTES + " WHERE idCliente = '"+idCliente+"';" );

            }
            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
            cursor.close();
        }

        return  correcto;



    }

    public boolean verificarDependenciaCliente(int idCliente){

        Boolean res = false;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = db.rawQuery("SELECT * FROM eventos WHERE idCliente = '" + idCliente + "';", null);
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

    public Cliente buscarCliente() {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cliente unCliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM clientes c inner join comerciales p on p.idCliente = c.idCliente;", null);
        if(cursor.moveToFirst()){

                unCliente = new Comercial();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((Comercial)unCliente).setRut(cursor.getString(5));
                ((Comercial)unCliente).setRazonSocial(cursor.getString(6));
        }
        cursor.close();
        //cursor = db.rawQuery("SELECT c.*, co.*  FROM " + TABLA_CLIENTES + " c INNER JOIN " + TABLA_PARTICULARES + " co ON co.idCliente = c.idCliente;", null);
        cursor = db.rawQuery("SELECT * FROM clientes c inner join particulares p on p.idCliente = c.idCliente;", null);

        if(cursor.moveToFirst()){
                unCliente = new Particular();
                unCliente.setIdCliente(cursor.getInt(0));
                unCliente.setDireccion(cursor.getString(1));
                unCliente.setTelefono(cursor.getString(2));
                unCliente.setCorreo(cursor.getString(3));
                ((Particular)unCliente).setNombre(cursor.getString(5));
                ((Particular)unCliente).setCedula(cursor.getString(6));

        }
        cursor.close();

        return unCliente;

    }
}
