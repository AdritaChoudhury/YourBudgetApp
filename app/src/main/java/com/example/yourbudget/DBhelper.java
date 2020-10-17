package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DBhelper extends SQLiteOpenHelper {

    //Database Information
    private static final String DATABASE_NAME = "budgetList.db";
    private static final int DATABASE_VERSION = 1;

    //List Table Name
    public static final String TABLE_LIST_NAME = "ListDetails";

    //List Column Names
    public static final String COL_LIST_ID = "id";
    public static final String COL_LIST_NAME = "name";
    public static final String COL_LIST_DURATION = "duration";
    public static final String COL_LIST_BUDGET = "budget";
    static final String[] list_columns = new String[]{DBhelper.COL_LIST_ID,
            DBhelper.COL_LIST_NAME,DBhelper.COL_LIST_DURATION,DBhelper.COL_LIST_BUDGET};

    //Item Table Name
    public static final String TABLE_NAME = "ListItems";

    //Column Names
    public static final String COL_ITEM_ID = "id";
    public static final String COL_ITEM_NAME = "name";
    public static final String COL_ITEM_COST = "cost";
    public static final String COL_ITEM_LIST_ID = "listId";
    static final String[] columns = new String[]{ DBhelper.COL_ITEM_ID,
            DBhelper.COL_ITEM_NAME,DBhelper.COL_ITEM_COST, DBhelper.COL_ITEM_LIST_ID};


    // creation SQLite statement
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_LIST_NAME
            + "(" + COL_LIST_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + COL_LIST_NAME + " TEXT NOT NULL, " + COL_LIST_DURATION + " INTEGER, " + COL_LIST_BUDGET + " INTEGER )";
    private static final String DATABASE_CREATE_ITEMS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "(" + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_ITEM_NAME + " TEXT NOT NULL, " + COL_ITEM_COST + " INTEGER, " + COL_ITEM_LIST_ID + " INTEGER )";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        System.out.println("Table Created");
        db.execSQL(DATABASE_CREATE_ITEMS);
        System.out.println("Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        System.out.println("DB Updated");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}