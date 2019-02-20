package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;

import com.launcher.ava.frequentlyUsedAppsScreen.AppFrequencyList;
import com.launcher.ava.frequentlyUsedAppsScreen.FrequentlyUsedAppsActivity;
import com.launcher.ava.helperApp.MainAppActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppFrequencyList.populate(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFontSize();
    }

    public void launchHelperApp(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(this, MainAppActivity.class);
        startActivity(intent);
    }

    public void launchFrequentlyUsedAppList(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(this, FrequentlyUsedAppsActivity.class);
        startActivity(intent);
    }


    public void launchAppDrawer(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(this, AppDrawer.class);
        startActivity(intent);
    }

    public void setFontSize() {
        Configuration config = new Configuration();
        config.fontScale = 4.0f;
        getResources().getConfiguration().setTo(config);
    }

    @Override
    public void onBackPressed() {
        // do nothing when back button is pressed from home page
    }

}