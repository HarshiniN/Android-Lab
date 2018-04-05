package com.example.rakshit.l5q1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
	Spinner spinVehicle;
	EditText editVNo;
	EditText editRCNo;
	Button btnSubmit;

	DataOps dataOps;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dataOps = new DataOps(this);

		spinVehicle = findViewById(R.id.spin_vehicle);
		editVNo = findViewById(R.id.edit_vno);
		editRCNo = findViewById(R.id.edit_rcno);
		btnSubmit = findViewById(R.id.btn_submit);

		btnSubmit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(editRCNo.getText().toString().isEmpty())
					editRCNo.setError("Enter RC number");
				else if(editVNo.getText().toString().isEmpty())
					editVNo.setError("Enter vehicle number");
				else
				{
					dataOps.setType(spinVehicle.getSelectedItemPosition());
					dataOps.setRCNo(editRCNo.getText().toString());
					dataOps.setVNo(editVNo.getText().toString());
					dataOps.save();

					startActivity(new Intent(MainActivity.this, EditActivity.class));
				}
			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item);
		adapter.addAll(getResources().getStringArray(R.array.vehicle_types));
		spinVehicle.setAdapter(adapter);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if(!dataOps.isEdit())
		{
			spinVehicle.setSelection(0);
			editRCNo.setText("");
			editVNo.setText("");
		}
	}
}
