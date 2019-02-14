package com.launcher.ava.frequentlyUsedAppsScreen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;

public class FrequentlyUsedAppsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frequently_used_apps);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        textViewToChange.setText(AppFrequencyList.getInstance().getHit(1));
    }


}
