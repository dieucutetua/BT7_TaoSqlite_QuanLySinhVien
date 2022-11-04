package com.example.sql_quanlysinhvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SinhVienDao extends SQLiteOpenHelper {
    public SinhVienDao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Truy van  khong tra ket qua
    public void QueryData(String sql){
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
    }
    //Truy van tra ket qua
    public Cursor GetData(String sql ){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}