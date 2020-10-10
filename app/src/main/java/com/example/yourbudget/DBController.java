package com.example.yourbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DBController {
    // Database fields
    private DBhelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBController(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void close() {
        dbHelper.close();
    }

    public void addItem(BudgetList list) {

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBhelper.COL_LIST_NAME, list.get_name());
        values.put(DBhelper.COL_LIST_COST, list.get_cost());

        database.insert(DBhelper.TABLE_NAME, null, values);

        System.out.println("Record Added");
        database.close();
    }

    public BudgetList getItem(int _id) {

        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DBhelper.TABLE_NAME, DBhelper.columns, DBhelper.COL_LIST_ID + " =?", new String[]{String.valueOf(_id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        BudgetList list = new BudgetList(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Integer.parseInt(cursor.getString(2)));

        return list;
    }

    // Getting All Items
    public List<BudgetList> getAllItems() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<BudgetList> contactList = new ArrayList<BudgetList>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DBhelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BudgetList list = new BudgetList();
                list.set_id(Integer.parseInt(cursor.getString(0)));
                list.set_name(cursor.getString(1));
                list.set_cost(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                contactList.add(list);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single item
    public int updateItem(BudgetList list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBhelper.COL_LIST_NAME, list.get_name());
        values.put(DBhelper.COL_LIST_COST, list.get_cost());

        // updating row
        return db.update(DBhelper.TABLE_NAME, values, DBhelper.COL_LIST_ID + " = ?",
                new String[]{String.valueOf(list.get_id())});
    }

    // Deleting single item
    public void deleteItem(BudgetList list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBhelper.TABLE_NAME, DBhelper.COL_LIST_ID + " = ?",
                new String[]{String.valueOf(list.get_id())});

        System.out.println("Record Deleted");
        db.close();
    }

    // Deleting single item
    public void deleteItem(int _id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(DBhelper.TABLE_NAME, DBhelper.COL_LIST_ID + " = ?",
                new String[]{String.valueOf(_id)});
        db.close();
    }
}