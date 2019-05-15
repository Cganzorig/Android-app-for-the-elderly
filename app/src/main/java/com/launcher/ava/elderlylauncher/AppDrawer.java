package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.utilities.RAdapter;
import com.launcher.ava.utilities.RemoveStatusBar;

public class AppDrawer extends AppCompatActivity {

  public static boolean isDeleteMode;
  public static final int UNINSTALL_APP_REQUEST = 1;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // make sure any new apps are captured here
    AppFrequencyList.populate(this);

    RemoveStatusBar.remove(this);
    setContentView(R.layout.activity_app_drawer);
    RecyclerView recyclerView = findViewById(R.id.RView);

    Intent thisIntent = getIntent();
    if (thisIntent.getIntExtra("deleteMode", 0) == 1) {
      isDeleteMode = true;
      TextView title = findViewById(R.id.textPhone);
      title.setText("SELECT APP TO DELETE");
    }
    else {
      isDeleteMode = false;
      TextView title = findViewById(R.id.textPhone);
      title.setText("LIST OF APPS");
    }

    RAdapter radapter = new RAdapter(this);
    recyclerView.setAdapter(radapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == UNINSTALL_APP_REQUEST) {
      Intent intent = new Intent(this, FirstAppScreen.class);
      startActivity(intent);
      // make sure to close app drawer so it refreshes
      finish();
    }
  }
}
