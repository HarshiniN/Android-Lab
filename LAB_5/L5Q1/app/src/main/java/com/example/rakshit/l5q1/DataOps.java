package com.example.rakshit.l5q1;

import android.content.Context;
import android.content.SharedPreferences;

public class DataOps
{
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	Context context;

	private int type;
	private String VNo;
	private String RCNo;
	private boolean edit;

	public DataOps(Context context)
	{
		this.context = context;
		preferences = context.getSharedPreferences("Data", 0);
		editor = preferences.edit();
	}

	public int getType()
	{
		return preferences.getInt("type", 0);
	}

	public void setType(int type)
	{
		editor.putInt("type", type);
	}

	public String getVNo()
	{
		return preferences.getString("VNO", "");
	}

	public void setVNo(String VNo)
	{
		editor.putString("VNO", VNo);
	}

	public String getRCNo()
	{
		return preferences.getString("RCNO", "");
	}

	public void setRCNo(String RCNo)
	{
		editor.putString("RCNO", RCNo);
	}

	public boolean isEdit()
	{
		return preferences.getBoolean("edit", false);
	}

	public void setEdit(boolean edit)
	{
		editor.putBoolean("edit", edit);
	}

	public void save()
	{
		editor.commit();
	}

	public void clear()
	{
		editor.clear();
		editor.commit();
	}
}
