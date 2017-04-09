package com.guangdong.greens.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by cat6572325 on 17-4-2.
 */

public class Sqlit_ extends SQLiteOpenHelper {

    public Sqlit_(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Sqlit_(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, "GreensDataBase", factory, 1, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history(_id integer primary key,imageUrls text,title text explain text");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(HashMap<String, String> map) {
        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imageUrls", map.get("imageJsons").toString()); //将数据中的两个图片地址用json保存起来，它最起码要有两个张
        values.put("title", map.get("title").toString());
        values.put("explain", map.get("explain").toString());
        db.insert("history", null, values);
        db.close();
    }

    public HashMap<String, Object> cursorData() {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        HashMap<String, Object> map = new HashMap<>();
        cursor = db.rawQuery("select * from history", null);
        while (cursor.moveToNext()) {
            map.put("imageUrls", cursor.getString(1));
            map.put("title", cursor.getString(2));
            map.put("explain", cursor.getString(3));
        }
        return map;
    }
}
