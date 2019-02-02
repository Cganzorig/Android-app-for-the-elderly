package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.launcher.ava.helperApp.MainAppActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchHelperApp(View view) {
        Intent intent = new Intent(this, MainAppActivity.class);
        startActivity(intent);
    }


    public void goToAnActivity(View view) {
        Intent intent = new Intent(this, AppDrawer.class);
        startActivity(intent);
    }
}
