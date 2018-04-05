package com.example.reubenabraham.androidpractice;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HERE WE WRITE MAIN CODE

       // ArrayList<String> abc= new ArrayList<String>();

        //SQLiteDatabase db= openOrCreateDatabase("reuben",MODE_PRIVATE,null);
        //db.execSQL();
        Button b=(Button)findViewById(R.id.but);
       //code for spinner
        String places[] = {"Select Place","Mangalore","Bangalore","Mumbai","Pune","Chennai","Hyderabad"};
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,places);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        //spinner1.getSelectedItem().toString()



        //code for listview
        String planets[]={"planet1","planet2","planet3"};
        ListView l1= (ListView)findViewById(R.id.list1);
        ArrayAdapter<String> listadapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,planets);
       l1.setAdapter(listadapter);





        //code for button
        Button b1=(Button)findViewById(R.id.button1);






        //time to make a calender
            final EditText text = (EditText) findViewById(R.id.editText);
            final Calendar calender = Calendar.getInstance();

            String myFormat = "dd/MM/yyyy";
            final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            text.setText(sdf.format(calender.getTime()));

            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    calender.set(Calendar.YEAR, year);
                    calender.set(Calendar.MONTH, month);
                    calender.set(Calendar.DAY_OF_MONTH, day);
                    text.setText(sdf.format(calender.getTime()));
                }


            };
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(MainActivity.this, date, calender
                            .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                            calender.get(Calendar.DAY_OF_MONTH)).show();
                }

            });

            //---------------------- now activity 1 is full


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.add)
        {
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            //intent.putExtra("Class","add");
            startActivity(intent);
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }







    public void click(View view)
    {
       // Toast.makeText(this,"bootay",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
}
