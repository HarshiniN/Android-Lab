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

import com.example.lucifer.lab_8_1.data.ContactContract.ContactEntry;
import com.example.lucifer.lab_8_1.data.ContactData;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    private int id;
    private boolean hasChanged = false;

    private EditText name;
    private EditText phone_no;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        name = findViewById(R.id.edit_name);
        phone_no = findViewById(R.id.edit_phone_no);
        email = findViewById(R.id.edit_email);
        if (getIntent().hasExtra("Edit_Contact"))
            setupData();
        else

        name.setOnClickListener(clickListener);
        phone_no.setOnClickListener(clickListener);
        email.setOnClickListener(clickListener);
    }

    private void setupData(){
        this.id = getIntent().getIntExtra("Contact_ID", 0);

        String[] projection = {
                ContactEntry._ID,
                ContactEntry.COLUMN_NAME,
                ContactEntry.COLUMN_PHONE_NO,
                ContactEntry.COLUMN_EMAIL};

        Cursor c = getContentResolver().query(ContentUris.withAppendedId(ContactEntry.CONTENT_URI, id), projection, null, null, null, null);
        c.moveToNext();
        Log.i("Cursor Postion", String.valueOf(c.getPosition()));
        name.setText(c.getString(c.getColumnIndex(projection[1])));
        phone_no.setText(c.getString(c.getColumnIndex(projection[2])));
        email.setText(c.getString(c.getColumnIndex(projection[3])));
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

        if (getIntent().hasExtra("Add_Contact")) {
            menu.findItem(R.id.action_delete).setVisible(false);
            getSupportActionBar().setTitle("Add a Contact");
        }
        else if (getIntent().hasExtra("Edit_Contact")){
            menu.findItem(R.id.action_delete).setVisible(true);
            getSupportActionBar().setTitle("Edit Contact");
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
                    ContactData contact = new ContactData();
                    contact.setid(this.id);
                    contact.setName(name.getText().toString().trim());
                    contact.setPhone_no(phone_no.getText().toString().trim());
                    contact.setEmail(email.getText().toString().trim());
                    i.putExtra("ContactData", contact);
                    setResult(RESULT_OK, i);
                    finish();
                    return true;
                }
                else
                    return false;

            // Respond to a clickListener on the "Delete" menu option
            case R.id.action_delete:
                i.putExtra("Delete", true);
                i.putExtra("Pet_id", this.id);
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
        else if(phone_no.getText().toString().isEmpty()) {
            phone_no.setError("Enter Phone No");
            return false;
        }
        else if(email.getText().toString().isEmpty()){
            email.setError("Enter Email");
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
                case R.id.edit_email:
                    ((TextInputLayout) findViewById(R.id.weightInputLayout)).setErrorEnabled(false);
                    break;
            }
        }
    };
}