package com.example.rakshit.l3q1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		ArrayList<String> list = new ArrayList<>(20);
		for(int i=0; i<20;i++)
			list.add("hello");

		RecyclerView listView = findViewById(R.id.list);
		listView.setLayoutManager(new GridLayoutManager(this, 4));
		listView.setAdapter(new Adapter(list, this));
	}

	class Adapter extends RecyclerView.Adapter<Adapter.Holder>
	{
		ArrayList<String> list;
		Context context;
		public Adapter(ArrayList<String> list, Context context)
		{
			this.list = list;
			this.context = context;
		}

		@Override
		public Holder onCreateViewHolder(ViewGroup parent, int viewType)
		{
			return new Holder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false));
		}

		@Override
		public void onBindViewHolder(Holder holder, int position)
		{
			holder.textView.setText(list.get(position));
		}

		@Override
		public int getItemCount()
		{
			return list.size();
		}

		class Holder extends RecyclerView.ViewHolder
		{
			public TextView textView;
			public Holder(View itemView)
			{
				super(itemView);
				textView = itemView.findViewById(android.R.id.text1);
			}
		}
	}
}
