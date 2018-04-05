package com.example.rakshit.l5q3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
	Spinner spinSrc;
	Spinner spinDst;
	EditText editDate;
	EditText editTime;
	ToggleButton toggle;
	Button btnSubmit;
	Button btnClear;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinSrc = findViewById(R.id.spin_src);
		spinDst = findViewById(R.id.spin_dest);
		editDate = findViewById(R.id.edit_date);
		editTime = findViewById(R.id.edit_time);
		toggle = findViewById(R.id.toggle_ticket);
		btnClear = findViewById(R.id.btn_clear);
		btnSubmit = findViewById(R.id.btn_submit);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item);
		adapter1.addAll(getResources().getStringArray(R.array.places));
		spinSrc.setAdapter(adapter1);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item);
		adapter2.addAll(getResources().getStringArray(R.array.places));
		spinDst.setAdapter(adapter2);

		final Calendar calendar1 = Calendar.getInstance();
		final Calendar calendar2 = Calendar.getInstance();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		editTime.setText(timeFormat.format(calendar2.getTimeInMillis()));
		editDate.setText(dateFormat.format(calendar1.getTimeInMillis()));

		final DatePickerDialog.OnDateSetListener dateChangedListener = new DatePickerDialog.OnDateSetListener()
		{
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
			{
				calendar1.set(Calendar.YEAR, year);
				calendar1.set(Calendar.MONTH, month);
				calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				String toDisplay = dateFormat.format(new Date(calendar1.getTimeInMillis()));
				editDate.setText(toDisplay);
			}
		};

		editDate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new DatePickerDialog(MainActivity.this, dateChangedListener, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		final TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
		{
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute)
			{
				calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar2.set(Calendar.MINUTE, minute);
				String toDisplay = timeFormat.format(new Date(calendar2.getTimeInMillis()));
				editTime.setText(toDisplay);
			}
		};

		editTime.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new TimePickerDialog(MainActivity.this, timeSetListener, calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE), true).show();
			}
		});


		btnClear.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				editDate.setText(dateFormat.format(Calendar.getInstance().getTimeInMillis()));
				editTime.setText(timeFormat.format(Calendar.getInstance().getTimeInMillis()));
				spinDst.setSelection(0);
				spinSrc.setSelection(0);
			}
		});

		btnSubmit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				if(toggle.isChecked() && Integer.parseInt(editTime.getText().toString().split(":")[0])<11)
					Toast.makeText(MainActivity.this, "Time has to be more than 11am if booking in Tatkal", Toast.LENGTH_SHORT).show();
				else
				{
					Intent intent = new Intent(MainActivity.this, ViewActivity.class);
					intent.putExtra("type", toggle.isChecked());
					intent.putExtra("date", editDate.getText().toString());
					intent.putExtra("time", editTime.getText().toString());
					intent.putExtra("src", spinSrc.getSelectedItemPosition());
					intent.putExtra("dst", spinDst.getSelectedItemPosition());
					startActivity(intent);
				}
			}
		});


	}
}
