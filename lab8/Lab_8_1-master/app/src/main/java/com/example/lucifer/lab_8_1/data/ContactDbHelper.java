package com.example.lucifer.lab_8_1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.lucifer.lab_8_1.data.ContactContract.SQL_CREATE_TABLE;
import static com.example.lucifer.lab_8_1.data.ContactContract.SQL_DELETE_TABLE;

/**
 * Created by adarsh on 21/05/2017.
 */

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ContactBook.db";
    private static final int DATABASE_VERSION = 1;

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
