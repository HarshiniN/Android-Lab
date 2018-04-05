package com.example.rakshit.l4q2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{
	Button button;
	ToggleButton toggleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = findViewById(R.id.btn);
		toggleButton = findViewById(R.id.toggle_btn);

		final ImageView imageView = new ImageView(this);
		final Toast toast = new Toast(this);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(imageView);

		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				imageView.setImageResource(R.drawable.ic_android_24dp);
				toast.show();
			}
		});

		toggleButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toggleButton.isChecked())
				{
					imageView.setImageResource(R.drawable.ic_on_24dp);
					toast.show();
				}
				else
				{
					imageView.setImageResource(R.drawable.ic_off_24dp);
					toast.show();
				}
			}
		});
	}
}
