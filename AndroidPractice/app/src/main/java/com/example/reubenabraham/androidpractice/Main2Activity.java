package com.example.reubenabraham.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Bundle bundle = getIntent().getExtras();
       // final String source = bundle.getString("Class");
        //Toast.makeText(this,source,Toast.LENGTH_SHORT).show();

        String planets[]={"reuben","pai","jajjo","kcs"};
        ListView l2= (ListView)findViewById(R.id.list2);
        ArrayAdapter<String> listadapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,planets);
        l2.setAdapter(listadapter);



        //------------------------------------------

        //a popup menu is basically like a context menu for a view- like a button
        //lets try adding a context menu for a button
        Button b2=(Button)findViewById(R.id.button2);
        registerForContextMenu(b2);


        //now to make a context menu for this list view
        registerForContextMenu(l2);
        //NOTE TO MAKE A CONTEXT MENU, WE NEED TO MAKE ANOTHER XML FILE
        //THIS CALL ONCREATECONTEXTMENU FUNCTION


    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId()==R.id.button2)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu2, menu);
        }
        else {
            MenuInflater inflater = getMenuInflater();
            //menuinfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            //menuinfo we dont need right now. basically we need it to see which listview item has called the context menu
            inflater.inflate(R.menu.menu2, menu);
        }
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId()==R.id.delete)
        {
            Toast.makeText(this,"deleting",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.edit)
        {
            Toast.makeText(this,"edit",Toast.LENGTH_SHORT).show();

        }
        else {
            return super.onContextItemSelected(item);
        }

        return super.onContextItemSelected(item);
    }



}
