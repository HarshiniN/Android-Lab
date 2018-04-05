package com.example.rakshit.l5q2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		editDate.setText(dateFormat.format(calendar.getTimeInMillis()));

		final DatePickerDialog.OnDateSetListener dateChangedListener = new DatePickerDialog.OnDateSetListener()
		{
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
			{
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				String toDisplay = dateFormat.format(new Date(calendar.getTimeInMillis()));
				editDate.setText(toDisplay);
			}
		};

		editDate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new DatePickerDialog(MainActivity.this, dateChangedListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});


		btnClear.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				editDate.setText(dateFormat.format(Calendar.getInstance().getTimeInMillis()));
				spinDst.setSelection(0);
				spinSrc.setSelection(0);
			}
		});

		btnSubmit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, ViewActivity.class);
				intent.putExtra("type", toggle.isChecked());
				intent.putExtra("date", editDate.getText().toString());
				intent.putExtra("src", spinSrc.getSelectedItemPosition());
				intent.putExtra("dst", spinDst.getSelectedItemPosition());
				startActivity(intent);
			}
		});
	}
}
