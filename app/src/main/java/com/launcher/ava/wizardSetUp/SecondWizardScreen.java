package com.launcher.ava.wizardSetUp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.FirstPhoneScreen;
import com.launcher.ava.elderlylauncher.MainActivity;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.utilities.ContactInfoTable;
import com.launcher.ava.utilities.ContactTableRow;
import com.launcher.ava.utilities.Website;

public class SecondWizardScreen extends AppCompatActivity {

  static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;  // The request code

  ConstraintLayout addAndRemove;
  ConstraintLayout whiteBlock;
  Button nextBtn;
  int websitecount;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    this.addAndRemove = findViewById(R.id.cLayoutSecondWizardBtn);
    this.whiteBlock = findViewById(R.id.cLayoutSecondWizardWhiteBlock);
    this.nextBtn= findViewById(R.id.secondNextBtn);
    this.websitecount = 0;

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_wizard_screen);
    displayFavouriteWebsites();
  }

  @Override
  protected void onResume() {
    super.onResume();
    displayFavouriteWebsites();
  }

  public void pressFav(View view) {
    this.websitecount += 1;
    pickFromList();
  }

  public void pickFromList() {
    Intent intent = new Intent(this, WebsiteDrawer.class);
    startActivityForResult(intent, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (ContextCompat.checkSelfPermission(this,
      Manifest.permission.READ_CONTACTS)
      != PackageManager.PERMISSION_GRANTED) {

      // No explanation needed; request the permission
      ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.READ_CONTACTS},
        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

      // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
      // app-defined int constant. The callback method gets the
      // result of the request.

    } else {
      handleResult(requestCode, resultCode, data);
    }
  }

  private void handleResult(int requestCode, int resultCode, Intent data) {

    if (this.websitecount > 3) {
      this.websitecount -= 3;
    }

    if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS && resultCode == RESULT_OK) {
      // temp housing for data of selected contact
      Website tmpwebsite = new Website();

      // data is returned from startActivityFromResult()
      Bundle extras = getIntent().getExtras();

      if (extras != null) {
        tmpwebsite.name = extras.getString("name");
        tmpwebsite.url = extras.getString("url");
        tmpwebsite.logoId = extras.getInt("logo");
      }

      switch ((this.websitecount)) {
        case 1:
          putWebInfoInSharedPrefs("buttonWeb1", tmpwebsite);
          break;
        case 2:
          putWebInfoInSharedPrefs("buttonWeb2", tmpwebsite);
          break;
        case 3:
          putWebInfoInSharedPrefs("buttonWeb3", tmpwebsite);
          break;
      }
      displayFavouriteWebsites();
    }
  }



  public void putWebInfoInSharedPrefs(String sharedPrefName, Website website) {
    SharedPreferences sp = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("webName", website.name);
    editor.putString("webUrl", website.url);
    editor.putString("webID", website.logoId.toString());
    editor.apply();
  }


  public void displayFavouriteWebsites() {
    TextView tv = findViewById(R.id.secondNextBtn);
    SharedPreferences sp1 = getSharedPreferences("buttonWeb1", Context.MODE_PRIVATE);
    TextView tv1 = findViewById(R.id.textFirstFavSecondWizard);
    if (sp1.contains("webName")) {
      String s = sp1.getString("webName", "");

      tv1.setText(s);
      tv.setText("Next");

    }else {
      tv1.setText(getResources().getString(R.string.add_fav_website));
    }

    SharedPreferences sp2 = getSharedPreferences("buttonWeb2", Context.MODE_PRIVATE);
    TextView tv2 = findViewById(R.id.textSecondFavSecondWizard);
    if (sp2.contains("webName")) {
      String s = sp2.getString("webName", "");
      tv2.setText(s);
      tv.setText("Next");

    }else {
      tv2.setText(getResources().getString(R.string.add_fav_website));
    }

    SharedPreferences sp3 = getSharedPreferences("buttonWeb3", Context.MODE_PRIVATE);
    TextView tv3 = findViewById(R.id.textThirdFavSecondWizard);
    if (sp3.contains("webName")) {
      String s = sp3.getString("webName", "");
      tv3.setText(s);
      tv.setText("Next");

    }else {
      tv3.setText(getResources().getString(R.string.add_fav_website));
    }

  }


  public void doNothing(View view) {
  }

  public void goToNextPage(View view) {
    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
    if (launchesOnlyOnce.isFirstTime()) {
      launchesOnlyOnce.setFirstTime(false);
    }
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }


}

