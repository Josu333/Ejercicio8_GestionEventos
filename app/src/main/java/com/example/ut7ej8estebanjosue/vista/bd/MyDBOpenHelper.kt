package com.example.ut7ej8estebanjosue.vista.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "UtEj8EstebanJosue.db"

        val TABLA_EVENTOS = "eventos"
        val ID = "id"
        val FECHA_EVENTO="fecha_evento"
        val HORA="hora"
        val TITULO="titulo"
        val DESCRIPCION ="descripcion"

        val TABLA_USUARIOS="usuarios"
        val ID_USUARIO ="idUsuario"
        val LOGIN ="login"
        val CONTRASENA="contrasena"
        val PERFIL ="perfil"

        val TABLA_EVENTOS_USU="eventosUsu"
        val ID_EU="idEU"
        val ID_U_FK="idU_FK"
        val ID_E_FK="idE_FK"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val crearTablas = listOf<String>(

            " CREATE TABLE $TABLA_EVENTOS ("+
                    "$ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "$FECHA_EVENTO TEXT, "+
                    "$HORA TEXT, "+
                    "$TITULO TEXT, "+
                    "$DESCRIPCION TEXT) ",

            "CREATE TABLE $TABLA_USUARIOS ("+
                    "$ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "$LOGIN TEXT, "+
                    "$CONTRASENA TEXT, "+
                    "$PERFIL TEXT) ", /*A--> ADMIN -- U-> ESTANDAR*/

            "CREATE TABLE $TABLA_EVENTOS_USU ("+
                    "$ID_EU INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "$ID_U_FK INTEGER NOT NULL, "+
                    "$ID_E_FK INTEGER NOT NULL) "

        )
        for (tabla in crearTablas){
            db!!.execSQL(tabla)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}