package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.launcher.ava.frequentlyUsedAppsScreen.AppFrequencyList;

import java.util.ArrayList;
import java.util.List;

public class AppDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_drawer);
        // Change the background of the app drawer to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RView);
        com.launcher.ava.elderlylauncher.RAdapter radapter = new com.launcher.ava.elderlylauncher.RAdapter(this);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
