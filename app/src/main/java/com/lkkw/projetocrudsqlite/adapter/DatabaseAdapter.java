package com.lkkw.projetocrudsqlite.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrudCompleto.DB";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contato" +
                      " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                      " nome TEXT," +
                      " email TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS contato";
        db.execSQL(sql);
        onCreate(db);
    }
}
