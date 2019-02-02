package com.launcher.ava.elderlylauncher;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AppDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_drawer);
        // Change the background of the app drawer to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RView);
        com.example.launcher_find_app.RAdapter radapter = new com.example.launcher_find_app.RAdapter(this);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
