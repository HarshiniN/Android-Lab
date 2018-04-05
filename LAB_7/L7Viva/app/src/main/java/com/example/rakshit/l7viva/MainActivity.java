package com.example.rakshit.l7viva;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1022);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
		{
			VideoView videoView = findViewById(R.id.videoView);
			MediaController mediaController = new MediaController(this);
			mediaController.setAnchorView(videoView);
			videoView.setMediaController(mediaController);

			Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/VID_20161011_231753.mp4");
			videoView.setVideoURI(uri);
			videoView.start();
		}
	}
}
