package com.example.asus.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timeTextView;

    public void updateTimer(int secondLeft){
        int minutes=(int) secondLeft/60;
        int seconds=secondLeft-minutes*60;


        String secondString=Integer.toString(seconds);
        if ((secondString=="0")){
            secondString="00";

        }else if (seconds<=9)
        {
            secondString="0"+secondString;
        }

        timeTextView.setText(Integer.toString(minutes)+":"+(secondString));

    }


    public void timerControlBtn(View view){
        new CountDownTimer(seekBar.getProgress()*1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                updateTimer((int) millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                timeTextView.setText("0:00");

                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mediaPlayer.start();

            }
        }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        timeTextView=(TextView) findViewById(R.id.timerView);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
