package com.example.capitalesAfrica

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DbHandler(context: Context?) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_Africa + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PAIS + " TEXT," + KEY_CAPITAL + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Elimina la tabla antigua si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Africa)
        // Crea la tabla otra vez
        onCreate(db)
    }

    // Elimina la tabla
    fun deleteAll () {
        val db = this.writableDatabase
        db.execSQL("delete from "+ TABLE_Africa);
    }

    // Se añaden los paises y capitales a la tabla
    fun insertData(id: Int?, pais: String?, capital: String?) {
        //Hace que la tabla esté en modo de escritura
        val db = this.writableDatabase
        val cValues = ContentValues()
        //Se le asignan valores a cada columna
        cValues.put(KEY_ID, id)
        cValues.put(KEY_PAIS, pais)
        cValues.put(KEY_CAPITAL, capital)
        // Inserta una nueva fila con los valores anteriormente asignados
        val newRowId = db.insert(TABLE_Africa, null, cValues)
        db.close()
    }

    // Se obtienen todos los registros
    val paisCapital: ArrayList<HashMap<String, String>>
        get() {
            val db = this.writableDatabase
            val listaPaisCapital = ArrayList<HashMap<String, String>>()
            val query = "SELECT pais, capital FROM " + TABLE_Africa
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val mapPaises = HashMap<String, String>()
                mapPaises["pais"] = cursor.getString(cursor.getColumnIndex(KEY_PAIS))
                mapPaises["capital"] = cursor.getString(cursor.getColumnIndex(KEY_CAPITAL))
                listaPaisCapital.add(mapPaises)
            }
            return listaPaisCapital
        }

    // Se declaran las constantes usadas para la base de datos
    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "africadb"
        private const val TABLE_Africa = "pais_capital"
        private const val KEY_ID = "id"
        private const val KEY_PAIS = "pais"
        private const val KEY_CAPITAL = "capital"
    }
}