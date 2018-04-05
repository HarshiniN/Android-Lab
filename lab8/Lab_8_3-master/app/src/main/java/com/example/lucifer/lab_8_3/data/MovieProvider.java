package com.example.lucifer.lab_8_3.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lucifer.lab_8_3.data.MovieContract.MovieEntry;

/**
 * Created by adarsh on 22/05/2017.
 */

public class MovieProvider extends ContentProvider {
    public static final String LOG_TAG = MovieProvider.class.getSimpleName();

    private MovieDbHelper mDbHelper;
    private static final int PET = 100,
                             MOVIE_ID = 101;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_MOVIE_REVIEWS, PET);
        URI_MATCHER.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_MOVIE_REVIEWS + "/#", MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new MovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        switch (URI_MATCHER.match(uri)){
            case PET:
                cursor =  db.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case MOVIE_ID:
                selection = MovieEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf((ContentUris.parseId(uri)))};
                cursor =  db.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)){
            case PET:
                return MovieEntry.CONTENT_ITEM_TYPE;
            case MOVIE_ID:
                return MovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri result;
        if(validData(values)) {
            long id = db.insert(MovieEntry.TABLE_NAME, null, values);
            result =  ContentUris.withAppendedId(uri, id);
        }
        else {
            Log.e(LOG_TAG, "Failed to Insert Row for URI : " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(result, null);
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int res = 0;
        switch (URI_MATCHER.match(uri)){
            case PET:
                res = db.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case MOVIE_ID:
                selection = MovieEntry._ID + " = ?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                res = db.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unable to Delete for URI " + uri);
        }

        if(res != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return res;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(validData(values)) {
            int res = 0;

            switch (URI_MATCHER.match(uri)){
                case PET:
                    res = db.update(MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                    break;

                case MOVIE_ID:
                    selection = MovieEntry._ID + " = ?";
                    selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                    res = db.update(MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                    break;

                default:
                    throw new IllegalArgumentException("Unable to Update for URI " + uri);
            }

            if(res != 0)
                getContext().getContentResolver().notifyChange(uri, null);

            return res;
        }
        else {
            Log.e(LOG_TAG, "Failed to Update Row for URI : " + uri);
            return -1;
        }
    }

    private boolean validData(ContentValues values){
        if (values.containsKey(MovieEntry.COLUMN_NAME) && values.getAsString(MovieEntry.COLUMN_NAME).isEmpty())
            throw new IllegalArgumentException("Requires a Name.");

        else if (values.containsKey(MovieEntry.COLUMN_YEAR) && values.getAsString(MovieEntry.COLUMN_YEAR).isEmpty())
            throw new IllegalArgumentException("Requires a Phone No.");

        else if (values.containsKey(MovieEntry.COLUMN_RATING) && values.getAsString(MovieEntry.COLUMN_RATING).isEmpty())
            throw new IllegalArgumentException("Requires and Email.");
        return true;
    }
}
