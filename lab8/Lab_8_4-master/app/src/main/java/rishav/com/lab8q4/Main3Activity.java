package rishav.com.lab8q4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5;
    SQLiteDatabase db;
    Button b,b1;
    EditText et1,et2,et3,et4;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1=(TextView)findViewById(R.id.textView2);
        tv2=(TextView)findViewById(R.id.textView4);
        tv3=(TextView)findViewById(R.id.textView6);
        tv4=(TextView)findViewById(R.id.textView8);
        tv5=(TextView)findViewById(R.id.textView10);
        et1=(EditText)findViewById(R.id.editText6);
        et2=(EditText)findViewById(R.id.editText7);
        et3=(EditText)findViewById(R.id.editText8);
        et4=(EditText)findViewById(R.id.editText9);
        et1.setVisibility(View.INVISIBLE);
        et2.setVisibility(View.INVISIBLE);
        et3.setVisibility(View.INVISIBLE);
        et4.setVisibility(View.INVISIBLE);
        b=(Button)findViewById(R.id.button2);
        b1=(Button)findViewById(R.id.button3);
        b1.setVisibility(View.INVISIBLE);
        id=Main2Activity.id;
        db=openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        Cursor c=db.rawQuery("select * from students where studentid='"+id+"'",null );
        if(c.moveToNext())
        {
            tv1.setText(id);
            tv2.setText(c.getString(0));
            tv3.setText(c.getString(2));
            tv4.setText(c.getString(3));
            tv5.setText(c.getString(4));
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                tv4.setVisibility(View.INVISIBLE);
                tv5.setVisibility(View.INVISIBLE);
                et1.setVisibility(View.VISIBLE);
                et2.setVisibility(View.VISIBLE);
                et3.setVisibility(View.VISIBLE);
                et4.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b.setVisibility(View.INVISIBLE);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2,s3,s4;
                s1=et1.getText()+"";
                s2=et2.getText()+"";
                s3=et3.getText()+"";
                s4=et4.getText()+"";
                ContentValues cv = new ContentValues();
                cv.put("name",s1); //These Fields should be your String values of actual column names
                cv.put("semester",s2);
                cv.put("branch",s3);
                cv.put("faculty_incharge",s4);
                db.update("students", cv, "studentid='"+id+"'", null);
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(intent);
                finish();
                //Cursor c=db.rawQuery("update students set name='"+s1+" ' , semester='"+s2+"',branch='"+s3+"" +
                  //      "',faculty_incharge='"+s4+"' where studentid ='"+id+"'",null);
            }
        });
    }
}
