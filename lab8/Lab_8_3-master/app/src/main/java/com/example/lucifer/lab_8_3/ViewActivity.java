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
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.lucifer.lab_8_3.data.MovieContract.MovieEntry;
import com.example.lucifer.lab_8_3.data.MovieData;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class ViewActivity extends AppCompatActivity {

    private int id;
    private boolean hasChanged = false;

    private EditText name;
    private EditText year;
    private EditText rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        name = findViewById(R.id.edit_name);
        year = findViewById(R.id.edit_phone_no);
        rating = findViewById(R.id.edit_rating);
        if (getIntent().hasExtra("View_Movie"))
            setupData();
        else

        name.setOnClickListener(clickListener);
        year.setOnClickListener(clickListener);
        rating.setOnClickListener(clickListener);
    }

    private void setupData(){
        this.id = getIntent().getIntExtra("Movie_ID", 0);

        String[] projection = {
                MovieEntry._ID,
                MovieEntry.COLUMN_NAME,
                MovieEntry.COLUMN_YEAR,
                MovieEntry.COLUMN_RATING};

        Cursor c = getContentResolver().query(ContentUris.withAppendedId(MovieEntry.CONTENT_URI, id), projection, null, null, null, null);
        c.moveToNext();
        Log.i("Cursor Postion", String.valueOf(c.getPosition()));
        name.setText(c.getString(c.getColumnIndex(projection[1])));
        year.setText(c.getString(c.getColumnIndex(projection[2])));
        rating.setText(c.getString(c.getColumnIndex(projection[3])));
        name.setEnabled(false);
        year.setEnabled(false);
        rating.setEnabled(false);
        c.close();
    }

    @Override
    public void onBackPressed() {
        if(!hasChanged) {
            super.onBackPressed();
            return;
        }

        showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);

        if (getIntent().hasExtra("Add_Movie")) {
            menu.findItem(R.id.action_delete).setVisible(false);
            getSupportActionBar().setTitle("Add a Movie Review");
        }
        else if (getIntent().hasExtra("View_Movie")){
            menu.findItem(R.id.action_delete).setVisible(true);
            getSupportActionBar().setTitle("Movie Review");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu

        Intent i = new Intent();

        switch (item.getItemId()) {
            // Respond to a clickListener on the "Save" menu option
            case R.id.action_save:
                if(validData()) {
                    MovieData contact = new MovieData();
                    contact.setid(this.id);
                    contact.setName(name.getText().toString().trim());
                    contact.setYear(year.getText().toString().trim());
                    contact.setRating(rating.getText().toString().trim());
                    i.putExtra("MovieData", contact);
                    setResult(RESULT_OK, i);
                    finish();
                    return true;
                }
                else
                    return false;

            // Respond to a clickListener on the "Delete" menu option
            case R.id.action_delete:
                i.putExtra("Delete", true);
                i.putExtra("Movie_id", this.id);
                setResult(RESULT_OK, i);
                finish();
                return true;

            // Respond to a clickListener on the "Up" arrow button in the app bar
            case android.R.id.home:
                if(hasChanged) {
                    showUnsavedChangesDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NavUtils.navigateUpFromSameTask(getParent());
                        }
                    });
                    return true;
                }
                else
                    NavUtils.navigateUpFromSameTask(this);
        }
        return false;
    }

    private boolean isEmpty(String str){
        return (str.isEmpty() || Integer.parseInt(str) == 0);
    }

    private boolean validData() {
        if(name.getText().toString().isEmpty()) {
            name.setError("Enter Name");
            return false;
        }
        else if(year.getText().toString().isEmpty()) {
            year.setError("Enter Year");
            return false;
        }
        else if(rating.getText().toString().isEmpty()){
            rating.setError("Enter Rating");
            return false;
        }
        else return true;

    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardChangeListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard you Changes and Quit Editing");
        builder.setPositiveButton("DISCARD", discardChangeListener);
        builder.setNegativeButton("KEEP EDITING", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hasChanged = true;
            switch (view .getId()){
                case R.id.edit_name:
                    ((TextInputLayout) findViewById(R.id.nameInputLayout)).setErrorEnabled(false);
                    break;
                case R.id.edit_rating:
                    ((TextInputLayout) findViewById(R.id.weightInputLayout)).setErrorEnabled(false);
                    break;
            }
        }
    };
}