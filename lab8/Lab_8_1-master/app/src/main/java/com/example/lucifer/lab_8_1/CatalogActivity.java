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
package com.example.lucifer.lab_8_1;

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

import com.example.lucifer.lab_8_1.data.ContactContract.ContactEntry;
import com.example.lucifer.lab_8_1.data.ContactData;

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
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                intent.putExtra("Edit_Contact", true);
                Cursor c = cursorAdapter.getCursor();
                c.moveToPosition(position);

                intent.putExtra("Contact_ID", c.getInt(c.getColumnIndex(ContactEntry._ID)));
                startActivityForResult(intent, ACTIVITY_EDIT_CONTACT);

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                intent.putExtra("Add_Contact", true);
                startActivityForResult(intent, ACTIVITY_NEW_CONTACT);
            }
        });

        Bundle args = new Bundle();
        args.putString("URI", String.valueOf(ContactEntry.CONTENT_URI));
        args.putStringArray("projection", new String[] {ContactEntry._ID,
                                                        ContactEntry.COLUMN_NAME,
                                                        ContactEntry.COLUMN_PHONE_NO,
                                                        ContactEntry.COLUMN_EMAIL});
        args.putString("selection", null);
        args.putStringArray("selectionArgs", null);
        args.putString("sortOrder", ContactEntry._ID + " ASC");

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
                insertData(new ContactData("Name", "1234560789", "abc@xyz.com"), ACTIVITY_NEW_CONTACT);
                return true;

            case R.id.action_delete_all_entries:
                getContentResolver().delete(ContactEntry.CONTENT_URI, ContactEntry._ID + " > 0", null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == ACTIVITY_NEW_CONTACT){
                if(data.hasExtra("ContactData")) {
                    ContactData pet = data.getParcelableExtra("ContactData");
                    insertData(pet, requestCode);
                }
            }
            else if(requestCode == ACTIVITY_EDIT_CONTACT){
                if(data.hasExtra("Delete")){
                    getContentResolver().delete(
                            Uri.withAppendedPath(ContactEntry.CONTENT_URI, "/" + String.valueOf(data.getIntExtra("Pet_id", 0))),
                            null,
                            null);
                }
                else if (data.hasExtra("ContactData")){
                    ContactData pet = data.getParcelableExtra("ContactData");
                    insertData(pet, requestCode);
                }
            }
        }
    }

    private void insertData(ContactData contact, int requestCode){
        ContentValues values = new ContentValues();

        values.put(ContactEntry.COLUMN_NAME, contact.getName());
        values.put(ContactEntry.COLUMN_PHONE_NO, contact.getPhone_no());
        values.put(ContactEntry.COLUMN_EMAIL, contact.getEmail());

        if (requestCode == ACTIVITY_NEW_CONTACT) {
            long rowID = ContentUris.parseId(getContentResolver().insert(ContactEntry.CONTENT_URI, values));

            if (rowID != -1)
                Toast.makeText(getBaseContext(), "Contact Added", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "Error Adding Contact", Toast.LENGTH_LONG).show();
        }
        else if (requestCode == ACTIVITY_EDIT_CONTACT){
            long rows = getContentResolver().update(
                    Uri.withAppendedPath(ContactEntry.CONTENT_URI, "/" + String.valueOf(contact.get_id())),
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
