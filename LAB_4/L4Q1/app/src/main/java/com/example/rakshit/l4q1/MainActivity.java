package com.example.rakshit.l4q1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<String> list = new ArrayList<>();
		list.add("Donut"); list.add("Eclairs"); list.add("Froyo");
		list.add("Gingerbread"); list.add("Honeycomb"); list.add("Ice Cream Sandwich");
		list.add("Jelly Bean"); list.add("KitKat"); list.add("Lollipop");
		list.add("Marshmallow"); list.add("Nougat"); list.add("Oreo");

		ListAdapter adapter = new ListAdapter(this, 0, list);
		ListView listView = findViewById(R.id.list);
		listView.setAdapter(adapter);
	}
}
