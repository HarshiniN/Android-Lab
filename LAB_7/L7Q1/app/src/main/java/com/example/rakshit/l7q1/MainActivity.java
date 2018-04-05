package com.example.rakshit.l7q1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
	ArrayList<POJOPhone> data = new ArrayList<>(10);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		data.add(new POJOPhone("Rakshit Airtel", "9714069339"));
		data.add(new POJOPhone("Rakshit Jio", "8660966476"));
		data.add(new POJOPhone("Vamshi Jio", "7019793293"));
		data.add(new POJOPhone("Vamshi Airtel", "7416296656"));
		data.add(new POJOPhone("Kulpreet", "9787568785"));
		data.add(new POJOPhone("Adarsh Jio", "7894512522"));
		data.add(new POJOPhone("Adarsh Airtel", "8797554524"));
		data.add(new POJOPhone("Bhawesh", "9876548752"));
		data.add(new POJOPhone("Arnav", "8752145698"));
		data.add(new POJOPhone("Rishav", "9986248810"));

		PhoneAdapter adapter = new PhoneAdapter(this, 0, data);
		ListView list = findViewById(R.id.list_phone);
		list.setAdapter(adapter);
		registerForContextMenu(list);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.context, menu);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId())
		{
			case R.id.menu_msg:
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",
						data.get(info.position).getNum(), null)));
				return true;
			case R.id.menu_call:
				Toast.makeText(MainActivity.this, "Calling contact", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return true;

		}
	}
}
