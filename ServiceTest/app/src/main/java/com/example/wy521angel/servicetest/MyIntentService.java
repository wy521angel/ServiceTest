package com.example.wy521angel.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wy521angel on 2017/5/7.
 */
public class MyIntentService extends IntentService {
    private final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, bundle.getString("key", "默认值"));
            }
        }
    }
}
