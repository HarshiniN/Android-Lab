package rishav.com.lab8q4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lv;
    ArrayList al;
    static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=(ListView)findViewById(R.id.lv);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        registerForContextMenu(lv);
        Cursor c=db.rawQuery("select * from students",null);
        al=new ArrayList();
        while(c.moveToNext()){
            al.add(c.getString(1));
        }
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        String i=al.get(listPosition)+"";
        if(item.getTitle().equals("View"))
        {
            id=i;
            Intent i1=new Intent(Main2Activity.this,Main3Activity.class);
            startActivity(i1);
            //Toast.makeText(Main2Activity.this, ""+i,Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle().equals("Delete"))
        {
            db.delete("students", "studentid" + "='" + i+"'", null);
            Cursor c=db.rawQuery("select * from students",null);
            al=new ArrayList();
            while(c.moveToNext()){
                al.add(c.getString(1));
            }
            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,al);
            lv.setAdapter(adapter);
            //Toast.makeText(Main2Activity.this, "Delete",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
