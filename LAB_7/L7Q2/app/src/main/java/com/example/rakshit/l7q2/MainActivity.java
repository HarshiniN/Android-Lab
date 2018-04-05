package com.example.rakshit.l7q2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = findViewById(R.id.btn_menu);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
				popupMenu.inflate(R.menu.btn_menu);
				
				popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
				{
					@Override
					public boolean onMenuItemClick(MenuItem item)
					{
						switch(item.getItemId())
						{
							case R.id.menu_1:
								Toast.makeText(MainActivity.this, "One selected", Toast.LENGTH_SHORT).show();
								return true;

							case R.id.menu_2:
								Toast.makeText(MainActivity.this, "Two selected", Toast.LENGTH_SHORT).show();
								return true;

							case R.id.menu_3:
								Toast.makeText(MainActivity.this, "Three selected", Toast.LENGTH_SHORT).show();
								return true;

							default:
								return true;
						}
					}
				});

				popupMenu.show();
			}
		});
	}
}
