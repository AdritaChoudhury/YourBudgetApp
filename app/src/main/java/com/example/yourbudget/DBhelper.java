package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DBhelper extends SQLiteOpenHelper {

    //List Table Name
    public static final String TABLE_NAME = "List";
    //List Column Names
    public static final String COL_LIST_ID = "_id";
    public static final String COL_LIST_NAME = "item_name";
    public static final String COL_LIST_COST = "cost";
    static final String[] columns = new String[]{DBhelper.COL_LIST_ID,
            DBhelper.COL_LIST_NAME,DBhelper.COL_LIST_COST};
    //Database Information
    private static final String DATABASE_NAME = "budgetlist.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
            + "(" + COL_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LIST_NAME + " TEXT NOT NULL, " + COL_LIST_COST + " INTEGER )";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        System.out.println("Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        System.out.println("DB Updated");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}