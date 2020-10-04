package com.example.agenda_dmos5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agenda_dmos5.models.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    private SQLiteDatabase database;
    private SQLiteHelper helper;

    public ContatoDao(Context contex){
        helper = new SQLiteHelper(contex);
    }

    public void add(Contato contato) throws Exception {
        if(contato == null){
            throw new NullPointerException();
        }

        ContentValues valores = new ContentValues();
        valores.put(SQLiteHelper.COLUMN_NOME, contato.getNome());
        valores.put(SQLiteHelper.COLUMN_TELEFONE, contato.getTelefone());
        valores.put(SQLiteHelper.COLUMN_CELULAR, contato.getCelular());

        database = helper.getWritableDatabase();
        if(database.insert(SQLiteHelper.TABLE_NAME, null, valores) == -1){
            throw new Exception();
        }

        database.close();
    }

    public List<Contato> buscaTodos(){
        List<Contato> mLista = new ArrayList<>();
        Contato contato;
        Cursor mCursor;

        String colunas[] = new String[] {
                SQLiteHelper.COLUMN_NOME,
                SQLiteHelper.COLUMN_TELEFONE,
                SQLiteHelper.COLUMN_CELULAR
        };

        database = helper.getReadableDatabase();

        mCursor = database.query(
                SQLiteHelper.TABLE_NAME,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NOME
        );

        while (mCursor.moveToNext()){
            contato = new Contato(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2)
            );

            mLista.add(contato);
        }

        mCursor.close();
        database.close();
        return mLista;
    }


}
