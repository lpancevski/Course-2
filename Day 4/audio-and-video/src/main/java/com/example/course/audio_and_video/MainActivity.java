package com.example.course.audio_and_video;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;

    private VideoView myVideo;
    private Button bPlayAudio;
    private Button bPlayVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);


        bPlayVideo = (Button)findViewById(R.id.playvideo);
        bPlayAudio = (Button)findViewById(R.id.playaudio);
        myVideo = (VideoView) findViewById(R.id.video);


        bPlayAudio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ding);
                mediaPlayer.start();
            }
        });

        bPlayVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                        + R.raw.test);
                myVideo.setMediaController(new MediaController(MainActivity.this));
                myVideo.setVideoURI(video);
                myVideo.requestFocus();
                myVideo.start();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
