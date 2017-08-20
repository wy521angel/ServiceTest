package com.example.wy521angel.clienttest;

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

import com.example.wy521angel.remoteservicetest1.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private IMyAidlInterface myAidlInterface;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            int result = 0;
            try {
                result = myAidlInterface.plus(50, 50);
                String upperStr = myAidlInterface.toUpperCase("comes from ClientTest");
                Log.d(TAG, "result is " + result);
                Log.d(TAG, "upperStr is " + upperStr);
                Toast.makeText(MainActivity.this, "result is " + result + ";upperStr is " + upperStr, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.wy521angel.remoteservicetest1.IMyAidlInterface");
                intent.setPackage("com.example.wy521angel.remoteservicetest1");
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        });
    }
}
