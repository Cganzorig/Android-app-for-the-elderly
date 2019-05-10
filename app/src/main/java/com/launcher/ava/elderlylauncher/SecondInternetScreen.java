package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.launcher.ava.utilities.OnItemClick;
import com.launcher.ava.utilities.WebsiteAdapter;
import com.launcher.ava.utilities.WebsiteDatabase;

public class SecondInternetScreen extends AppCompatActivity implements OnItemClick {

  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  public String spName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_internet_screen);

    spName = getIntent().getStringExtra("selection");

    mRecyclerView = findViewById(R.id.website_recycler_view);
    mRecyclerView.setHasFixedSize(true);

    mLayoutManager = new LinearLayoutManager(this,
      LinearLayoutManager.VERTICAL,
      false);
    mRecyclerView.setLayoutManager(mLayoutManager);

    mAdapter = new WebsiteAdapter(WebsiteDatabase.getInstance(), this);
    mRecyclerView.setAdapter(mAdapter);

  }

  @Override
  public void onItemClicked(String[] itemsNames) {
    SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("webName", itemsNames[0]);
    editor.putString("webLogo", itemsNames[1]);
    editor.apply();
    Intent intent = new Intent(this, FirstInternetScreen.class);
    startActivity(intent);
    this.finish();
  }
}
