package com.example.rakshit.l6viva;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = findViewById(R.id.img);
		img.setTag(false);

		img.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				img.animate().alpha(0.0f).setDuration(1000).setListener(new Animator.AnimatorListener()
				{
					@Override
					public void onAnimationEnd(Animator animation)
					{
						if(!(boolean)img.getTag())
						{
							img.setImageResource(R.drawable.moon);
							img.setTag(true);
						}
						else
						{
							img.setImageResource(R.drawable.sun);
							img.setTag(false);
						}

						img.animate().alpha(1.0f).setDuration(1000).setListener(new Animator.AnimatorListener()
						{
							@Override
							public void onAnimationStart(Animator animation)
							{

							}

							@Override
							public void onAnimationEnd(Animator animation)
							{

							}

							@Override
							public void onAnimationCancel(Animator animation)
							{

							}

							@Override
							public void onAnimationRepeat(Animator animation)
							{

							}
						});
					}

					@Override
					public void onAnimationCancel(Animator animation)
					{   }

					@Override
					public void onAnimationRepeat(Animator animation)
					{   }

					@Override
					public void onAnimationStart(Animator animation)
					{	}
				});
			}
		});
	}
}
