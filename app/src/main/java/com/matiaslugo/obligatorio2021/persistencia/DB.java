package com.matiaslugo.obligatorio2021.persistencia;

import android.provider.BaseColumns;

 final class DB {

    public static final String DATA_BASE_NOMBRE = "empresa.db";
    public static final int DATA_BASE_VERSION = 1;

    public static final String TABLA_CLIENTES = "Clientes";
    public static final String TABLA_PARTICULARES = "Particulares";
    public static final String TABLA_COMERCIALES = "Comerciales";
    public static final String TABLA_REUNIONES = "Reuniones";
    public static final String TABLA_GASTOS = "Gastos";
    public static final String TABLA_TAREAS = "Tareas";
    public static final String TABLA_EVENTOS = "Eventos";






    private DB(){}
    //BaseColums incorpora _ID _COUNT
    public static abstract class Clientes implements BaseColumns {
        public static final String DIRECCION = "Direccion";
        public static final String TELEFONO = "Telefono";
        static final String CORREO = "Correo";

        public static  final String[] COLUMNAS = {_ID, DIRECCION,TELEFONO,CORREO};

        public static final String SQL_CREAR_TABLA_CLIENTES = new StringBuilder("CREATE TABLE ")
                .append(TABLA_CLIENTES).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(DIRECCION).append(" TEXT,")
                .append(TELEFONO).append(" TEXT, ")
                .append(CORREO).append(" TEXT);").toString();

    }

    public static abstract class Particulares implements BaseColumns{
        public static final String NOMBRE = "Nombre";
        public static final String CEDULA = "Cedula";
        public static final String[] COLUMNAS = {_ID,NOMBRE,CEDULA};

        public static final String SQL_CREAR_TABLA_PARTICULARES = new StringBuilder("CREATE TABLE ")
                .append(TABLA_PARTICULARES).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY, ")
                .append(NOMBRE).append(" TEXT,")
                .append(CEDULA).append(" TEXT,")
                .append(" FOREIGN KEY(").append(BaseColumns._ID)
                .append(") REFERENCES ").append(TABLA_CLIENTES).append("(").append(BaseColumns._ID)
                .append("));").toString();
    }

    public static abstract class Comerciales implements BaseColumns{
        public static final String RAZON_SOCIAL = "RazonSocial";
        public static final String RUT = "Rut";
        public static final String[] COLUMNAS = {_ID,RAZON_SOCIAL,RUT};

        public static final String SQL_CREAR_TABLA_COMERCIALES = new StringBuilder("CREATE TABLE ")
                .append(TABLA_COMERCIALES).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY, ")
                .append(RUT).append(" TEXT,")
                .append(RAZON_SOCIAL).append(" TEXT,")
                .append(" FOREIGN KEY(").append(BaseColumns._ID)
                .append(") REFERENCES ").append(TABLA_CLIENTES).append("(").append(BaseColumns._ID)
                .append("));").toString();
    }

    public static abstract class Eventos implements BaseColumns{
        public static final String FECHA = "Fecha";
        public static final String HORA = "Hora";
        public static final String DURACION = "Duracion";
        public static final String TITULO = "Titulo";
        public static final String TIPO = "Tipo";
        public static final String ASISTENTES = "cantAsistentes";
        public static final String IDCLIENTE = "idCliente";


        public static final String[] COLUMNAS = {_ID,FECHA,HORA,DURACION,TITULO,TIPO,
                ASISTENTES,IDCLIENTE};

        public static final String SQL_CREAR_TABLA_EVENTOS = new StringBuilder("CREATE TABLE ")
                .append(TABLA_EVENTOS).append(" (")
                .append(_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(FECHA).append(" TEXT,")
                .append(HORA).append(" TEXT,")
                .append(DURACION).append(" TEXT,")
                .append(TITULO).append(" TEXT,")
                .append(TIPO).append(" INTEGER,")
                .append(ASISTENTES).append(" INTEGER,")
                .append(IDCLIENTE).append(" INTEGER,")
                .append(" FOREIGN KEY (").append(IDCLIENTE)
                .append(") REFERENCES ").append(TABLA_CLIENTES).append(" (").append(_ID)
                .append("));").toString();


    }

    public static abstract class Reuniones implements BaseColumns{
        public static final String DESCRIPCION = "Descripcion";
        public static final String OBJETIVO = "Objetivo";
        public static final String FECHA = "Fecha";
        public static final String HORA = "Hora";
        public static final String LUGAR = "Lugar";
        public static final String NOTIFICAR_CLIENTE = "NotificarCliente";
        public static final String IDEVENTO = "idEvento";


        public static final String[] COLUMNAS = {_ID,DESCRIPCION,OBJETIVO,FECHA,HORA,LUGAR,
                NOTIFICAR_CLIENTE,IDEVENTO};

        public static final String SQL_CREAR_TABLA_REUNIONES = new StringBuilder("CREATE TABLE ")
                .append(TABLA_REUNIONES).append(" (")
                .append(_ID).append(" INTEGER, ")
                .append(DESCRIPCION).append(" TEXT,")
                .append(OBJETIVO).append(" TEXT,")
                .append(FECHA).append(" TEXT,")
                .append(HORA).append(" TEXT,")
                .append(LUGAR).append(" TEXT,")
                .append(NOTIFICAR_CLIENTE).append(" BOOLEAN NOT NULL CHECK(").append(NOTIFICAR_CLIENTE).append(" in (0,1)),")
                .append(IDEVENTO).append(" INTEGER,")
                .append("FOREIGN KEY (").append(IDEVENTO)
                .append(") REFERENCES ").append(TABLA_EVENTOS).append(" (").append(_ID).append("), ")
                .append(" PRIMARY KEY (").append(IDEVENTO).append(",").append(_ID).append("));").toString();
    }

    public static abstract class Gastos implements BaseColumns{
        public static final String MOTIVO = "Motivo";
        public static final String PROVEEDOR = "Proveedor";
        public static final String MONTO = "Monto";
        public static final String IDEVENTO = "idEvento";


        public static final String[] COLUMNAS = {_ID,MOTIVO,PROVEEDOR,MONTO,IDEVENTO};

        public static final String SQL_CREAR_TABLA_GASTOS = new StringBuilder("CREATE TABLE ")
                .append(TABLA_GASTOS).append(" (")
                .append(_ID).append(" INTEGER , ")
                .append(MOTIVO).append(" TEXT,")
                .append(PROVEEDOR).append(" TEXT,")
                .append(MONTO).append(" REAL,")
                .append(IDEVENTO).append(" INTEGER,")
                .append("FOREIGN KEY (").append(IDEVENTO)
                .append(") REFERENCES ").append(TABLA_EVENTOS).append("(").append(_ID).append("), ")
                .append(" PRIMARY KEY (").append(IDEVENTO).append(",").append(_ID).append("));").toString();
    }

    public static abstract class Tareas implements BaseColumns{
        public static final String DESCIPCION = "Descripcion";
        public static final String FECHALIMITE = "FechaLimite";
        public static final String REALIZADA = "Realizada";
        public static final String IDEVENTO = "idEvento";


        public static final String[] COLUMNAS = {_ID,DESCIPCION,FECHALIMITE,REALIZADA,IDEVENTO};

        public static final String SQL_CREAR_TABLA_TAREAS = new StringBuilder("CREATE TABLE ")
                .append(TABLA_TAREAS).append(" (")
                .append(_ID).append(" INTEGER , ")
                .append(DESCIPCION).append(" TEXT,")
                .append(FECHALIMITE).append(" TEXT,")
                .append(REALIZADA).append(" BOOLEAN NOT NULL CHECK(").append(REALIZADA).append(" in (0,1)),")
                .append(IDEVENTO).append(" INTEGER,")
                .append("FOREIGN KEY (").append(IDEVENTO)
                .append(") REFERENCES ").append(TABLA_EVENTOS).append("(").append(_ID).append("), ")
                .append(" PRIMARY KEY (").append(IDEVENTO).append(",").append(_ID).append("));").toString();
    }




}
