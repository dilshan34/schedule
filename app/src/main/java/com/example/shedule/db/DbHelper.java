package com.example.shedule.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String CREATE_EMP_TABLE = "CREATE TABLE shedule(id TEXT PRIMARY KEY ,fromTime TEXT, toTime TEXT )";



    public DbHelper(Context context) {
        super(context, "Empdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL(CREATE_EMP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String id,String fromTime,String toTime,SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("fromTime",fromTime);
        contentValues.put("toTime",toTime);

        db.replace("shedule", null, contentValues);

    }

    public Cursor getAllShedules(SQLiteDatabase database) {
        String[] projection = {"id", "fromTime", "toTime"};
        Cursor cursor = database.query("shedule", projection, null, null, null, null, null);
        return cursor;
    }
}
