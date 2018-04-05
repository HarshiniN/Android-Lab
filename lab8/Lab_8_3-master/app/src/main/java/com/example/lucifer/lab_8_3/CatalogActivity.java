/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lucifer.lab_8_3;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucifer.lab_8_3.data.MovieContract.MovieEntry;
import com.example.lucifer.lab_8_3.data.MovieData;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int ACTIVITY_NEW_CONTACT = 1;
    private static final int ACTIVITY_EDIT_CONTACT = 2;
    private static final int LOADER_ID = 1001;

    private ContactAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        cursorAdapter = new ContactAdapter(this, null);
        ((ListView) findViewById(R.id.not_empty)).setEmptyView(findViewById(R.id.empty));
        ((ListView) findViewById(R.id.not_empty)).setAdapter(cursorAdapter);
        ((ListView) findViewById(R.id.not_empty)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, ViewActivity.class);
                intent.putExtra("View_Movie", true);
                Cursor c = cursorAdapter.getCursor();
                c.moveToPosition(position);

                intent.putExtra("Movie_ID", c.getInt(c.getColumnIndex(MovieEntry._ID)));
                startActivityForResult(intent, ACTIVITY_EDIT_CONTACT);

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, ViewActivity.class);
                intent.putExtra("Add_Movie", true);
                startActivityForResult(intent, ACTIVITY_NEW_CONTACT);
            }
        });

        Bundle args = new Bundle();
        args.putString("URI", String.valueOf(MovieEntry.CONTENT_URI));
        args.putStringArray("projection", new String[] {MovieEntry._ID,
                                                        MovieEntry.COLUMN_NAME});
        args.putString("selection", null);
        args.putStringArray("selectionArgs", null);
        args.putString("sortOrder", MovieEntry._ID + " ASC");

        getSupportLoaderManager().initLoader(LOADER_ID, args, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertData(new MovieData("Name", "2018", "3"), ACTIVITY_NEW_CONTACT);
                return true;

            case R.id.action_delete_all_entries:
                getContentResolver().delete(MovieEntry.CONTENT_URI, MovieEntry._ID + " > 0", null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == ACTIVITY_NEW_CONTACT){
                if(data.hasExtra("MovieData")) {
                    MovieData pet = data.getParcelableExtra("MovieData");
                    insertData(pet, requestCode);
                }
            }
            else if(requestCode == ACTIVITY_EDIT_CONTACT){
                if(data.hasExtra("Delete")){
                    getContentResolver().delete(
                            Uri.withAppendedPath(MovieEntry.CONTENT_URI, "/" + String.valueOf(data.getIntExtra("Movie_id", 0))),
                            null,
                            null);
                }
                else if (data.hasExtra("MovieData")){
                    MovieData pet = data.getParcelableExtra("MovieData");
                    insertData(pet, requestCode);
                }
            }
        }
    }

    private void insertData(MovieData contact, int requestCode){
        ContentValues values = new ContentValues();

        values.put(MovieEntry.COLUMN_NAME, contact.getName());
        values.put(MovieEntry.COLUMN_YEAR, contact.getYear());
        values.put(MovieEntry.COLUMN_RATING, contact.getRating());

        if (requestCode == ACTIVITY_NEW_CONTACT) {
            long rowID = ContentUris.parseId(getContentResolver().insert(MovieEntry.CONTENT_URI, values));

            if (rowID != -1)
                Toast.makeText(getBaseContext(), "Review Added", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "Error Adding Review", Toast.LENGTH_LONG).show();
        }
        else if (requestCode == ACTIVITY_EDIT_CONTACT){
            long rows = getContentResolver().update(
                    Uri.withAppendedPath(MovieEntry.CONTENT_URI, "/" + String.valueOf(contact.get_id())),
                    values,
                    null,
                    null
            );
            if (rows != -1)
                Toast.makeText(getBaseContext(), "Pet Info Updated", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "Error Updating Pet Info", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri URI = Uri.parse(args.getString("URI"));
        String[] projection = args.getStringArray("projection");
        String selection = args.getString("selection");
        String[] selectionArgs = args.getStringArray("selectionArgs");
        String sortOrder = args.getString("sortOrder");

        return new CursorLoader(this, URI, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
