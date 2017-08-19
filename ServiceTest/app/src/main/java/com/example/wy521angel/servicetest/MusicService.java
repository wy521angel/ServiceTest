package com.example.wy521angel.servicetest;

import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by wy521angel on 2017/5/5.
 */

public class MusicService extends Service {

    private final String TAG = "MusicService";

    private MediaPlayer mediaPlayer;

    private int startId;

    public enum Control {
        PLAY, PAUSE, STOP
    }

    @Override
    public void onCreate() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.setLooping(false);
        }
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.e(TAG, "onStartCommand---startId: " + startId);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Control control = (Control) bundle.getSerializable("Key");
            if (control != null) {
                switch (control) {
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.mipmap.hebe);
            mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.hebe));
            mBuilder.setContentTitle("你就不要想起我");
            mBuilder.setContentText("田馥甄 渺小专辑");
            startForeground(1, mBuilder.build());
        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
