package com.example.wy521angel.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wy521angel on 2017/5/5.
 */
public class StartIntentServiceActivity extends AppCompatActivity {

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_intent_service);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", "当前值：" + i++);
        intent.putExtras(bundle);
        startService(intent);
    }
}
