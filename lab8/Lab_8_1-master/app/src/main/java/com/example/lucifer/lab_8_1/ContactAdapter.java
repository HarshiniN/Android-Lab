package com.example.lucifer.lab_8_1;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucifer.lab_8_1.data.ContactContract;

/**
 * Created by adarsh on 23/05/2017.
 */

public class ContactAdapter extends CursorAdapter {
    public ContactAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView)view.findViewById(R.id.contact_name)).setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME)));
        ((TextView)view.findViewById(R.id.contact_phone_no)).setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE_NO)));
        ((TextView)view.findViewById(R.id.contact_email)).setText(cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMAIL)));
    }
}
