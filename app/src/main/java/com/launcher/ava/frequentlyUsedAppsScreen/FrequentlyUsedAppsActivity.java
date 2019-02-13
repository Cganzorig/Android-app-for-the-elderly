package com.launcher.ava.frequentlyUsedAppsScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;

public class FrequentlyUsedAppsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppFrequencyList list = new AppFrequencyList(this);

        setContentView(R.layout.activity_frequently_used_apps);

        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        textViewToChange.setText(list.topHit());
    }


}
