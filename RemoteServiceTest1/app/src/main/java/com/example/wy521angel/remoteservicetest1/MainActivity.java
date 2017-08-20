package com.example.wy521angel.remoteservicetest1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private MyService.MyBinder myBinder;
    private boolean isBind;
    private IMyAidlInterface myAidlInterface;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            myBinder = (MyService.MyBinder) service;
//            myBinder.startDownload();
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            try {
                int result = myAidlInterface.plus(3,5);
                String upperStr = myAidlInterface.toUpperCase("hello jay");
                Log.d(TAG, "result is " + result);
                Log.d(TAG, "upperStr is " + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "process id is " + android.os.Process.myPid());
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        if (isBind) {
            unbindService(mConnection);
            isBind = false;
        } else {
            showToast("服务未绑定");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind) {
            unbindService(mConnection);
            isBind = false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
