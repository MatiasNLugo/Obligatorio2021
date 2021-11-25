package com.matiaslugo.obligatorio2021.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATA_BASE_VERSION = 1;
    private static final String DATA_BASE_NOMBRE = "empresa.db";
    protected static final String TABLA_CLIENTES = "clientes";
    private static final String QUERY_TABLA_CLIENTES = "CREATE TABLE " + TABLA_CLIENTES +
            "(idCliente INTEGER PRIMARY KEY AUTOINCREMENT,direccion TEXT, telefono TEXT, correo TEXT)"; //, activo INTEGER DEFAULT 1 opcional
    protected static final String TABLA_PARTICULARES = "particulares";
    protected static final String TABLA_COMERCIALES = "comerciales";
    protected static final String TABLA_REUNIONES = "reuniones";
    protected static final String TABLA_GASTOS = "gastos";
    protected static final String TABLA_TAREAS = "tareas";
    protected static final String TABLA_EVENTOS = "eventos";

    private static final int ID_REUNIONES = 1;
    private static final int ID_GASTOS = 1;
    private static final int ID_TAREAS = 1;



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATA_BASE_NOMBRE, null, DATA_BASE_VERSION);
    }

    // Llamado la primera vez que se accede a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(QUERY_TABLA_CLIENTES);
        db.execSQL("CREATE TABLE " + TABLA_PARTICULARES + "(" +
                "idCliente INTEGER ,"+
                "nombre TEXT," +
                "cedula TEXT," +
                "FOREIGN KEY (idCliente) REFERENCES "+TABLA_CLIENTES+"(idCliente))");
        db.execSQL("CREATE TABLE " + TABLA_COMERCIALES + "(" +
                "idCliente INTEGER ,"+
                "rut TEXT," +
                "razonSocial TEXT," +
                "FOREIGN KEY (idCliente) REFERENCES "+TABLA_CLIENTES+"(idCliente))");
        db.execSQL("CREATE TABLE " + TABLA_EVENTOS + "(" +
                "idEvento INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "fecha TEXT," +
                "hora TEXT," +
                "duracion TEXT," +
                "titulo TEXT," +
                "tipo INTEGER," +
                "cantAsistentes INTEGER," +
                "idCliente INTEGER," +
                "FOREIGN KEY (idCliente) REFERENCES "+TABLA_CLIENTES+"(idCliente))");
        db.execSQL("CREATE TABLE " + TABLA_REUNIONES + "(" +
                "idReunion INTEGER NOT NULL UNIQUE,"+
                "descripcion TEXT," +
                "objetivo TEXT," +
                "fecha TEXT," +
                "HORA TEXT," +
                "lugar TEXT," +
                "notificarCliente BOOLEAN NOT NULL CHECK(notificarCliente IN (0,1))," +
                "idEvento INTEGER," +
                "FOREIGN KEY (idEvento) REFERENCES "+TABLA_EVENTOS+"(idEvento),"+
                "PRIMARY KEY (idEvento,idReunion))");
        db.execSQL("CREATE TABLE " + TABLA_GASTOS + "(" +
                "idGasto INTEGER NOT NULL UNIQUE,"+
                "motivo TEXT," +
                "proveedor TEXT," +
                "monto REAL," +
                "idEvento INTEGER," +
                "FOREIGN KEY (idEvento) REFERENCES "+TABLA_EVENTOS+"(idEvento),"+
                "PRIMARY KEY (idEvento,idGasto))");
        db.execSQL("CREATE TABLE " + TABLA_TAREAS + "(" +
                "idTarea INTEGER NOT NULL UNIQUE,"+
                "descripcion TEXT," +
                "fechaLimite TEXT," +
                "idEvento INTEGER," +
                "realizada INTEGER DEFAULT 0,"+
                "FOREIGN KEY  (idEvento) REFERENCES "+TABLA_EVENTOS+"(idEvento),"+
                "PRIMARY KEY (idEvento,idTarea))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+ TABLA_TAREAS);
        db.execSQL("DROP TABLE "+ TABLA_GASTOS);
        db.execSQL("DROP TABLE "+ TABLA_REUNIONES);
        db.execSQL("DROP TABLE "+ TABLA_EVENTOS);
        db.execSQL("DROP TABLE "+ TABLA_COMERCIALES);
        db.execSQL("DROP TABLE "+ TABLA_PARTICULARES);
        db.execSQL("DROP TABLE "+ TABLA_CLIENTES);

        onCreate(db);
    }
}
