package com.example.rakshit.l3q1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, ListActivity.class));
			}
		});

		findViewById(R.id.btn_grid).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, GridActivity.class));
			}
		});

		findViewById(R.id.btn_tab).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, TabActivity.class));
			}
		});

		findViewById(R.id.btn_table).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, TableActivity.class));
			}
		});
	}
}
