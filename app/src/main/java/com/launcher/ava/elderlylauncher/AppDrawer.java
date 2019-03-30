package com.launcher.ava.elderlylauncher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.launcher.ava.utilities.RAdapter;
import com.launcher.ava.utilities.RemoveStatusBar;

public class AppDrawer extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    RemoveStatusBar.remove(this);
    setContentView(R.layout.activity_app_drawer);
    RecyclerView recyclerView = findViewById(R.id.RView);
    RAdapter radapter = new RAdapter(this);
    recyclerView.setAdapter(radapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
}
