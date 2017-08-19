package com.example.wy521angel.servicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStartServiceActivity(View view) {
        startActivity(new Intent(this, StartServiceActivity.class));
    }

    public void startStartIntentServiceActivity(View view) {
        startActivity(new Intent(this, StartIntentServiceActivity.class));
    }

    public void startBindServiceActivity(View view) {
        startActivity(new Intent(this, BindServiceActivity.class));
    }
}
