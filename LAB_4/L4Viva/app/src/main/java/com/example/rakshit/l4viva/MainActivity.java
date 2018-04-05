package com.example.rakshit.l4viva;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	EditText input;
	Button guess;
	private int answer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		input = findViewById(R.id.input);
		guess = findViewById(R.id.btn);
		generateRandomNumber();

		guess.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkAnswer(Integer.parseInt(input.getText().toString()));
			}
		});
	}

	private int generateRandomNumber()
	{
		Random ran = new Random();
		return ran.nextInt(21);
	}

	private void checkAnswer(int num)
	{
		if(num==answer)
		{
			answer = generateRandomNumber();
			Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
		}
		else if(num>answer)
			Toast.makeText(this, "Lower", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Higher", Toast.LENGTH_SHORT).show();
	}
}
