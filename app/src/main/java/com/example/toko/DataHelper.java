package com.example.toko;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERTSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERTSION);
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        String sql = "create table data(id INTEGER PRIMARY KEY AUTOINCREMENT, namaPembeli TEXT, namaBarang TEXT, jumlahBarang DOUBLE, harga DOUBLE, uangBayar DOUBLE, TotalBayar DOUBLE,  Kembalian DOUBLE, Bonus TEXT, Keterangan TEXT);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

}
