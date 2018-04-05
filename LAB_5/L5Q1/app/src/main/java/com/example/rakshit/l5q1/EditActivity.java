package com.example.rakshit.l5q1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EditActivity extends AppCompatActivity
{
	TextView tvType;
	TextView tvVNo;
	TextView tvRCNo;
	Button btnEdit;
	Button btnConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		final DataOps dataOps = new DataOps(this);

		tvRCNo = findViewById(R.id.tv_rcno);
		tvVNo = findViewById(R.id.tv_vno);
		tvType = findViewById(R.id.tv_type);
		btnConfirm = findViewById(R.id.btn_confirm);
		btnEdit = findViewById(R.id.btn_edit);

		tvType.append(getResources().getStringArray(R.array.vehicle_types)[dataOps.getType()]);
		tvVNo.append(dataOps.getVNo());
		tvRCNo.append(dataOps.getRCNo());

		btnEdit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
				dataOps.setEdit(true);
				dataOps.save();
			}
		});

		btnConfirm.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dataOps.clear();
				Toast.makeText(EditActivity.this, "parking number: " + new Random().nextInt(20), Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}
