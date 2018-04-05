package com.wmps.paul_mac.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    Button play;
    Button pause;
    SeekBar music;
    SeekBar volume;
    MediaPlayer mp;
    Handler handle = new Handler();
    AudioManager am = null;

    double start = 0.0;
    double end = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        music = findViewById(R.id.mp);
        volume = findViewById(R.id.volume);

        mp = MediaPlayer.create(this,R.raw.song);
        music.setClickable(false);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                end = mp.getDuration();
                start = mp.getCurrentPosition();
                music.setProgress((int) start);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

    }


}
