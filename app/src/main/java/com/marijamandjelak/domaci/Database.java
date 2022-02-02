package com.marijamandjelak.domaci;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context) {
        super(context, "baza.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table korisnici (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        } catch (Exception e){

        }
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists korisnici");
            onCreate(db);
        } catch (Exception e){

        }
    }



    public boolean insert(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("username",username);
        data.put("password",password);
        long success = db.insert("korisnici", null, data);
        if(success == -1 ) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor view() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from korisnici", null);
        return c;
    }

    public boolean update(String id, String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data = new ContentValues();
        data.put("username",username);
        data.put("password",password);
        db.update("korisnici", data, "id = ?",new String[]{id});
        return true;
    }

    public Integer delete(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("korisnici", "id = ?", new String[]{id});
    }

    public boolean provera(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM korisnici WHERE username ='" + username + "' AND password='" + password + "'";

        Cursor c = db.rawQuery(select, null);

        if (c.moveToFirst()) {
            return true;
        }

        if(c!=null) {
            c.close();
        }
        db.close();
        return false;
    }
}
