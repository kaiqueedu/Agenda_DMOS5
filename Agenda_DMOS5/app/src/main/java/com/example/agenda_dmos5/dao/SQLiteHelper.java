package com.example.agenda_dmos5.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contato_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contatos";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_CELULAR = "celular";

    public SQLiteHelper(@Nullable Context contex){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase dbLite) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (";
        sql += COLUMN_NOME     + " TEXT NOT NULL, ";
        sql += COLUMN_TELEFONE + " TEXT NOT NULL, ";
        sql += COLUMN_CELULAR  + " TEXT NOT NULL";
        sql += ")";

        dbLite.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
