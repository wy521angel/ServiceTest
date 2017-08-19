package com.example.wy521angel.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by wy521angel on 2017/5/5.
 */
public class StartServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(this, MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MusicService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void pauseMusic(View view) {
        Intent intent = new Intent(this, MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MusicService.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void stopMusic(View view) {
        Intent intent = new Intent(this, MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", MusicService.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
        //或者是直接如下调用
        //Intent intent = new Intent(this, MusicService.class);
        //stopService(intent);
    }
}
