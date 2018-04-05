package com.example.lucifer.lab_8_1.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by adarsh on 21/05/2017.
 */

public class ContactContract {

    ContactContract(){}

    //BASE URI Details
    public static final String CONTENT_AUTHORITY = "com.example.lucifer.lab_8_1";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //CONTENT URI Details for Table contacts
    public static final String PATH_CONTACTS = "contacts";

    public static class ContactEntry implements BaseColumns {

        //CONTENT URI for the contacts Table
        public static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CONTACTS);

        //MIME type of CONTENT_URI for a list of contacts
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONTACTS;
        //MIME type of CONTENT_URI for a contact
        public static final String CONTENT_ITEM_TYPE =  ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONTACTS;

        public static final String TABLE_NAME = "contacts";         //Table Name

        public static final String _ID = BaseColumns._ID;       //ID column
        public static final String COLUMN_NAME = "name";        //name column
        public static final String COLUMN_PHONE_NO = "phone_no";      //phone_no column
        public static final String COLUMN_EMAIL = "email";    //email column
    }

    //CREATE TABLE statement
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + ContactEntry.TABLE_NAME + " (" +
                    ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ContactEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    ContactEntry.COLUMN_PHONE_NO + " TEXT NOT NULL, " +
                    ContactEntry.COLUMN_EMAIL + " TEXT NOT NULL);";

    //DELETE TABLE statement
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME;
}
