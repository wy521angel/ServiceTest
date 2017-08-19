package com.example.wy521angel.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by wy521angel on 2017/5/7.
 */
public class MyBindService extends Service {

    private IBinder myBinder;

    private Random mGenerator;

    private final String TAG = "MyBindService";

    public class MyBinder extends Binder {
        MyBindService getService() {
            return MyBindService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        myBinder = new MyBinder();
        mGenerator = new Random();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return true;
//        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
