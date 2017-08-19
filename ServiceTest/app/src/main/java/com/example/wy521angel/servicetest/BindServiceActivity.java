package com.example.wy521angel.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by wy521angel on 2017/5/5.
 */
public class BindServiceActivity extends AppCompatActivity {

    private MyBindService mService;

    private boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindService.MyBinder binder = (MyBindService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    public void bindService(View view) {
        Intent intent = new Intent(this, MyBindService.class);
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        } else {
            showToast("服务未绑定");
        }
    }

    public void getData(View view) {
        if (mBound) {
            showToast("获取到的随机数：" + mService.getRandomNumber());
        } else {
            showToast("服务未绑定");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void stopService(View view) {
        stopService(new Intent(this, MyBindService.class));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
