package com.launcher.ava.wizardSetUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.launcher.ava.elderlylauncher.FirstAppScreen;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.utilities.RAdapter;
import com.launcher.ava.utilities.RemoveStatusBar;
import com.launcher.ava.utilities.Website;
import com.launcher.ava.utilities.WebsiteAdapter;
import com.launcher.ava.utilities.WebsiteDatabase;

public class WebsiteDrawer extends AppCompatActivity {

  public static boolean isDeleteMode;

  static final int MY_PERMISSIONS_REQUEST_READ_WEBSITES = 1;  // The request code

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // make sure any new apps are captured here

    RemoveStatusBar.remove(this);
    setContentView(R.layout.activity_website_drawer);
    RecyclerView recyclerView = findViewById(R.id.WebView);

    Intent thisIntent = getIntent();
    if (thisIntent.getIntExtra("deleteMode", 0) == 1) {
      isDeleteMode = true;
    }
    else {
      isDeleteMode = false;
    }

    WebsiteAdapter wadapter = new WebsiteAdapter(WebsiteDatabase.getInstance(), this);
    recyclerView.setAdapter(wadapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }




  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == MY_PERMISSIONS_REQUEST_READ_WEBSITES) {
      Intent intent = new Intent(this, SecondWizardScreen.class);
      startActivity(intent);
      // make sure to close app drawer so it refreshes
      finish();
    }
  }



}
