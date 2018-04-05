package com.example.lucifer.lab_8_3.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by adarsh on 21/05/2017.
 */

public class MovieContract {

    MovieContract(){}

    //BASE URI Details
    public static final String CONTENT_AUTHORITY = "com.example.lucifer.lab_8_3";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //CONTENT URI Details for Table contacts
    public static final String PATH_MOVIE_REVIEWS = "movie_reviews";

    public static class MovieEntry implements BaseColumns {

        //CONTENT URI for the contacts Table
        public static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIE_REVIEWS);

        //MIME type of CONTENT_URI for a list of contacts
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_REVIEWS;
        //MIME type of CONTENT_URI for a contact
        public static final String CONTENT_ITEM_TYPE =  ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_REVIEWS;

        public static final String TABLE_NAME = "movies";         //Table Name

        public static final String _ID = BaseColumns._ID;       //ID column
        public static final String COLUMN_NAME = "name";        //name column
        public static final String COLUMN_YEAR = "year";      //phone_no column
        public static final String COLUMN_RATING = "rating";    //email column
    }

    //CREATE TABLE statement
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                    MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MovieEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    MovieEntry.COLUMN_YEAR + " TEXT NOT NULL, " +
                    MovieEntry.COLUMN_RATING + " TEXT NOT NULL);";

    //DELETE TABLE statement
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME;
}
