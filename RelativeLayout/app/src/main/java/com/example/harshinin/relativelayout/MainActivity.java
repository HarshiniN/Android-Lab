package com.example.harshinin.relativelayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button button = (Button) findViewById(R.id.button);
       final TextView tv = findViewById(R.id.url);
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                final String txt = (tv.getText()).toString();
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse(txt));
                startActivity(i);
            }
        });

    }
}
