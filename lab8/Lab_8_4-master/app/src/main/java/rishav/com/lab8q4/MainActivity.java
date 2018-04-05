package rishav.com.lab8q4;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button b;
    EditText n,si,s,br,fi;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        n=(EditText)findViewById(R.id.editText);
        si=(EditText)findViewById(R.id.editText2);
        s=(EditText)findViewById(R.id.editText3);
        br=(EditText)findViewById(R.id.editText4);
        fi=(EditText)findViewById(R.id.editText5);
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists students(name varchar(30), studentid varchar(30), semester varchar(5)" +
                ", branch varchar(20), faculty_incharge varchar(30)) ");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2,s3,s4,s5;
                s1=n.getText()+"";
                s2=si.getText()+"";
                s3=s.getText()+"";
                s4=br.getText()+"";
                s5=fi.getText()+"";
                db.execSQL("insert into students values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')");
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
