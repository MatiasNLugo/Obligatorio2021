package com.matiaslugo.obligatorio2021.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private Context contexto;



    public DataBaseHelper(@Nullable Context contexto) {
        super(contexto, DB.DATA_BASE_NOMBRE, null, DB.DATA_BASE_VERSION);
        this.contexto = contexto;

    }

    // Llamado la primera vez que se accede a la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB.Clientes.SQL_CREAR_TABLA_CLIENTES);
        db.execSQL(DB.Comerciales.SQL_CREAR_TABLA_COMERCIALES);
        db.execSQL(DB.Particulares.SQL_CREAR_TABLA_PARTICULARES);
        db.execSQL(DB.Eventos.SQL_CREAR_TABLA_EVENTOS);
        db.execSQL(DB.Reuniones.SQL_CREAR_TABLA_REUNIONES);
        db.execSQL(DB.Gastos.SQL_CREAR_TABLA_GASTOS);
        db.execSQL(DB.Tareas.SQL_CREAR_TABLA_TAREAS);

        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_CLIENTES).append(" VALUES(NULL,'Direccion 123','09229292','Correo@correo.com');").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_CLIENTES).append(" VALUES(NULL,'Direccion 999','09666666','Correo@correo.com');").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_CLIENTES).append(" VALUES(NULL,'Direccion 78b','09777777','Correo@correo.com');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_COMERCIALES).append(" VALUES(1,'45673772015','Empresa 2');").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_COMERCIALES).append(" VALUES(2,'1562783740012','Empresa 1');").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_PARTICULARES).append(" VALUES(3,'Jean Paul Sartre','768848391');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_EVENTOS).append(" VALUES(NULL,'13/01/2022','18:00','120','Titulo de Evento',4,0,1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(DB.TABLA_EVENTOS).append(" VALUES(NULL,'24/02/2022','13:00','60','Evento Corto',2,5,3);").toString());




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+ DB.TABLA_TAREAS);
        db.execSQL("DROP TABLE "+ DB.TABLA_GASTOS);
        db.execSQL("DROP TABLE "+ DB.TABLA_REUNIONES);
        db.execSQL("DROP TABLE "+ DB.TABLA_EVENTOS);
        db.execSQL("DROP TABLE "+ DB.TABLA_COMERCIALES);
        db.execSQL("DROP TABLE "+ DB.TABLA_PARTICULARES);
        db.execSQL("DROP TABLE "+ DB.TABLA_CLIENTES);

        onCreate(db);
    }
}
