package com.launcher.ava.helperApp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.launcher.ava.elderlylauncher.R;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        // Change the background of the app drawer to white
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
    }
}
