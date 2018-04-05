package com.example.rakshit.l5q3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity
{
	TextView tvSrc;
	TextView tvDst;
	TextView tvDate;
	TextView tvTime;
	TextView tvType;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);

		Intent i = getIntent();
		String src = getResources().getStringArray(R.array.places)[i.getIntExtra("src", 0)];
		String dst = getResources().getStringArray(R.array.places)[i.getIntExtra("dst", 0)];
		String date = i.getStringExtra("date");
		String time = i.getStringExtra("time");
		String type = !i.getBooleanExtra("type", false) ? "General": "Tatkal";

		tvType = findViewById(R.id.tv_type);
		tvSrc = findViewById(R.id.tv_src);
		tvDst = findViewById(R.id.tv_dst);
		tvDate = findViewById(R.id.tv_date);
		tvTime = findViewById(R.id.tv_time);

		tvDate.append(date);
		tvTime.append(time);
		tvDst.append(dst);
		tvSrc.append(src);
		tvType.append(type);
	}
}
