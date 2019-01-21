package com.example.tanmay.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView t;
    SeekBar seekBar;
    Boolean countDownActive= false;
    Button b;
    CountDownTimer c;
    ImageView i;

    public void update(int secRem){

        int min = secRem/60;
        int sec= secRem- min*60;
        String secString= Integer.toString(sec);
        if(secRem<=9)

           secString= "0"+secString;

            t.setText(Integer.toString(min)+" : " +secString);
    }

    public void control(View view){

        if(countDownActive==false) {

            countDownActive = true;
            seekBar.setEnabled(false);
            b.setText("STOP");
           c= new CountDownTimer(seekBar.getProgress() * 1000 + 200, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    Toast.makeText(MainActivity.this, "OVER", Toast.LENGTH_SHORT).show();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    i.setImageResource(R.drawable.omm);
                }
            }.start();
        }
        else
        {
            t.setText("0 : 30");
            seekBar.setProgress(30);
            c.cancel();
            b.setText("GO");
            seekBar.setEnabled(true);
            countDownActive= false;
            i.setImageResource(R.drawable.egg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         b = findViewById(R.id.button);
        t= findViewById(R.id.textView);
        i=findViewById(R.id.imageView2);
        seekBar=  findViewById(R.id.seekBar2);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               update(progress);
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
