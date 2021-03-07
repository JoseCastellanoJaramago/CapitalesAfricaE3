package com.example.africajava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tutlane on 06-01-2018.
 */

public class DbHandler extends SQLiteOpenHelper {
    // Se declaran las constantes usadas para la base de datos
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "africadb";
    private static final String TABLE_Africa = "pais_capital";
    private static final String KEY_ID = "id";
    private static final String KEY_PAIS = "pais";
    private static final String KEY_CAPITAL = "capital";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Africa + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PAIS + " TEXT," + KEY_CAPITAL + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Elimina la tabla antigua si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Africa);
        // Crea la tabla otra vez
        onCreate(db);
    }

    // Elimina la tabla
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_Africa);
    }

    // Se añaden los paises y capitales a la tabla
    void insertData(int id, String pais, String capital){
        //Hace que la tabla esté en modo de escritura
        SQLiteDatabase db = this.getWritableDatabase();
        //Se le asignan valores a cada columna
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ID, id);
        cValues.put(KEY_PAIS, pais);
        cValues.put(KEY_CAPITAL, capital);
        // Inserta una nueva fila con los valores anteriormente asignados
        long newRowId = db.insert(TABLE_Africa,null, cValues);
        db.close();
    }
    // Se obtienen todos los registros
    public ArrayList<HashMap<String, String>> paisCapital(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT pais, capital FROM "+ TABLE_Africa;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("pais",cursor.getString(cursor.getColumnIndex(KEY_PAIS)));
            user.put("capital",cursor.getString(cursor.getColumnIndex(KEY_CAPITAL)));
            userList.add(user);
        }
        return  userList;
    }
}
