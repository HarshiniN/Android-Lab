package com.example.rakshit.l3q1;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TabActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		TabLayout tabLayout = findViewById(R.id.tabs);
		ViewPager pager = findViewById(R.id.viewPager);
		FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return new Fragment();
			}

			@Override
			public int getCount()
			{
				return 3;
			}

			@Nullable
			@Override
			public CharSequence getPageTitle(int position)
			{
				if(position==0)
					return "TAB 1";
				else if(position==1)
					return "TAB 2";
				else
					return "TAB 3";
			}
		};

		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
	}
}
