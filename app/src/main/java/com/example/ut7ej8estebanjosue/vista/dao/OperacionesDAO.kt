package com.example.ut7ej8estebanjosue.vista.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ut7ej8estebanjosue.vista.bd.MyDBOpenHelper
import com.example.ut7ej8estebanjosue.vista.modelo.Eventos
import com.example.ut7ej8estebanjosue.vista.modelo.Usuarios

class OperacionesDAO(contexto: Context) {


    private val mBD: SQLiteDatabase


    init {
        val estructura = MyDBOpenHelper(
            contexto,
            MyDBOpenHelper.DATABASE_NAME,
            null,
            MyDBOpenHelper.DATABASE_VERSION
        )
        mBD = estructura.writableDatabase
    }

    fun addUsuario(datosUsuario: Array<String>) {

        val usuario = Usuarios(
            null,
            datosUsuario[0],
            datosUsuario[1],
            datosUsuario[2]
        )
        val values = ContentValues()

        values.put(MyDBOpenHelper.ID_USUARIO, usuario.id)
        values.put(MyDBOpenHelper.LOGIN, usuario.login)
        values.put(MyDBOpenHelper.CONTRASENA, usuario.contrasena)
        values.put(MyDBOpenHelper.PERFIL, usuario.perfil)

        mBD.insert(MyDBOpenHelper.TABLA_USUARIOS, null, values)

    }

    fun baseVacia(): Boolean {

        val vacia: Boolean
        val cursor: Cursor = mBD.query(
            MyDBOpenHelper.TABLA_USUARIOS, null, null,
            null, null, null, null
        )


        vacia = (!cursor.moveToFirst())

        return vacia
    }

    fun comprobarCredenciales(user: String, contra: String): Usuarios? {

        var u: Usuarios? = null

        val cursor: Cursor = mBD.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLA_USUARIOS} " +
                    "WHERE ${MyDBOpenHelper.LOGIN} = '$user' " +
                    "AND ${MyDBOpenHelper.CONTRASENA} = '$contra'",
            null
        )
        if (cursor.moveToFirst()) {
            u = Usuarios(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDBOpenHelper.ID_USUARIO)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.LOGIN)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.CONTRASENA)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.PERFIL))
            )
        }

        if (!cursor.isClosed)
            cursor.close()

        return u

    }

    fun existeUnEvento(fecha: String, hora: String): Boolean {

        val resultado: Boolean
        val cursor: Cursor = mBD.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLA_EVENTOS} " +
                    "WHERE ${MyDBOpenHelper.FECHA_EVENTO} = '$fecha' " +
                    "AND ${MyDBOpenHelper.HORA} = '$hora'", null
        )
        resultado = cursor.moveToFirst()
        cursor.close()

        return resultado

    }

    fun addEvento(newEvento: Eventos) {

        val values = ContentValues()

        values.put(MyDBOpenHelper.ID, newEvento.id)
        values.put(MyDBOpenHelper.FECHA_EVENTO, newEvento.fecha)
        values.put(MyDBOpenHelper.HORA, newEvento.hora)
        values.put(MyDBOpenHelper.TITULO, newEvento.nombre)
        values.put(MyDBOpenHelper.DESCRIPCION, newEvento.descripcion)

        mBD.insert(MyDBOpenHelper.TABLA_EVENTOS, null, values)

    }

    fun listaEventos(): MutableList<Eventos> {

        val lista: MutableList<Eventos> = ArrayList()
        var sql = "SELECT * FROM $"
        val cursor: Cursor = mBD.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLA_EVENTOS} " +
                    "ORDER BY ${MyDBOpenHelper.FECHA_EVENTO} ASC, ${MyDBOpenHelper.HORA} ASC", null
        )
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Eventos(
                        cursor.getInt(cursor.getColumnIndexOrThrow("${MyDBOpenHelper.ID}")),
                        cursor.getString(cursor.getColumnIndexOrThrow("${MyDBOpenHelper.FECHA_EVENTO}")),
                        cursor.getString(cursor.getColumnIndexOrThrow("${MyDBOpenHelper.HORA}")),
                        cursor.getString(cursor.getColumnIndexOrThrow("${MyDBOpenHelper.TITULO}")),
                        cursor.getString(cursor.getColumnIndexOrThrow("${MyDBOpenHelper.DESCRIPCION}"))

                    )
                )
            } while (cursor.moveToNext())

        }
        if (!cursor.isClosed)
            cursor.close()
        return lista

    }

    fun updateUnEvento(nombre: String, fecha: String, hora: String) {

        val values = ContentValues()
        values.put(MyDBOpenHelper.FECHA_EVENTO, fecha)
        values.put(MyDBOpenHelper.HORA, hora)
        mBD.update(
            MyDBOpenHelper.TABLA_EVENTOS,
            values, "${MyDBOpenHelper.TITULO} = '$nombre' ", null
        )

    }

    fun eventoSelected(codigoEvento: Int): Eventos {

        var event: Eventos? = null
        var sql = "select * from ${MyDBOpenHelper.TABLA_EVENTOS} where ${MyDBOpenHelper.ID} = " +
                "'$codigoEvento'"

        val cursor: Cursor = mBD.rawQuery(
            sql, null
        )

        if (cursor.moveToFirst()) {
            event = Eventos(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDBOpenHelper.ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.FECHA_EVENTO)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.HORA)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.TITULO)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDBOpenHelper.DESCRIPCION))
            )
        }
        if (!cursor.isClosed)
            cursor.close()

        return event!!

    }

    fun existeEU(codigoUsuario: Int, codigoEvento: Int): Boolean {

        val result: Boolean

        val sql = "SELECT * FROM ${MyDBOpenHelper.TABLA_EVENTOS_USU} " +
                "WHERE ${MyDBOpenHelper.ID_U_FK} = '$codigoUsuario' " +
                "AND ${MyDBOpenHelper.ID_E_FK} = '$codigoEvento'"
        val cursor: Cursor = mBD.rawQuery(
            sql, null
        )

        result = cursor.moveToFirst()
        cursor.close()

        return result
    }

    fun eliminarEU(codigoUsuario: Int, codigoEvento: Int) {

        val values = ContentValues()
        values.put(MyDBOpenHelper.ID_U_FK, codigoUsuario)
        values.put(MyDBOpenHelper.ID_E_FK, codigoEvento)

        
        mBD.delete(
            MyDBOpenHelper.TABLA_EVENTOS_USU,"${MyDBOpenHelper.ID_U_FK} = '$codigoUsuario'" +
                    "AND ${MyDBOpenHelper.ID_E_FK} = '$codigoEvento' ",
            null
        )

    }

    fun anadirEU(codigoUsuario: Int, codigoEvento: Int) {

        val values = ContentValues()
        values.put(MyDBOpenHelper.ID_U_FK, codigoUsuario)
        values.put(MyDBOpenHelper.ID_E_FK, codigoEvento)

        mBD.insert(MyDBOpenHelper.TABLA_EVENTOS_USU, null, values)

    }

}
