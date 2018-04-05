package com.example.rakshit.l7q1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends ArrayAdapter<POJOPhone>
{
	private Context context;
	private ArrayList<POJOPhone> list;

	public PhoneAdapter(@NonNull Context context, int resource, @NonNull ArrayList<POJOPhone> objects)
	{
		super(context, 0, objects);
		this.context = context;
		this.list = objects;
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		View v;
		if(convertView==null)
			v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
		else
			v = convertView;

		POJOPhone object = list.get(position);
		((TextView)v.findViewById(R.id.name)).setText(object.getName());
		((TextView)v.findViewById(R.id.phone_num)).setText(object.getNum());
		return v;
	}
}
