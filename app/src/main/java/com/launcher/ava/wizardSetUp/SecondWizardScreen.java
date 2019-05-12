package com.launcher.ava.wizardSetUp;

import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.DONE_WIZARD;
import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.ONE_WIZARD;
import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.TWO_WIZARD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.launcher.ava.elderlylauncher.MainActivity;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.elderlylauncher.SecondInternetScreen;
import com.launcher.ava.utilities.WebsiteDatabase;

public class SecondWizardScreen extends AppCompatActivity {

  private int numFavs;
  private int selectedButton;

  ConstraintLayout addAndRemove;
  ConstraintLayout whiteBlock;
  Button plusBtn;
  Button minusBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_wizard_screen);

    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
    launchesOnlyOnce.setPosition(TWO_WIZARD);

    this.addAndRemove = findViewById(R.id.cLayoutBtn);
    this.whiteBlock = findViewById(R.id.cLayoutInternetWhiteBlock);
    this.plusBtn = findViewById(R.id.add_button);
    this.minusBtn = findViewById(R.id.remove_button);

    displayFavouriteWebsites();
  }
  public void setWhiteBlocks() {
    LayoutParams paramsAddAndRemove = (LayoutParams) this.addAndRemove.getLayoutParams();
    LayoutParams paramsWhiteBlock = (LayoutParams) this.whiteBlock.getLayoutParams();
    TextView tv = findViewById(R.id.textExplainPlusMinus);
    LayoutParams paramsPlus = (LayoutParams) this.plusBtn.getLayoutParams();
    LayoutParams paramsMinus = (LayoutParams) this.minusBtn.getLayoutParams();

    switch (this.numFavs) {
      case 0:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide1;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide2;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide2;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.INVISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_fav_web);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical6;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical5;
        break;
      case 1:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide2;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide3;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide3;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav_website);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical1;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical2;
        paramsMinus.startToStart = R.id.firstPhoneScreenVertical3;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical4;
        break;
      case 2:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide3;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide4;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide4;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav_website);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical1;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical2;
        paramsMinus.startToStart = R.id.firstPhoneScreenVertical3;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical4;
        break;
      case 3:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide4;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide5;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide5;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.INVISIBLE);
        tv.setText(R.string.remove_fav_website);

        paramsMinus.startToStart = R.id.firstPhoneScreenVertical6;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical5;
        break;

    }
    addAndRemove.setLayoutParams(paramsAddAndRemove);
    whiteBlock.setLayoutParams(paramsWhiteBlock);
    plusBtn.setLayoutParams(paramsPlus);
    minusBtn.setLayoutParams(paramsMinus);
  }

  public void pickFromWebsiteList(String spName) {
    Intent intent = new Intent(this, SecondInternetScreen.class);
    intent.putExtra("selection", spName);
    intent.putExtra("calledByWizard", "YES");
    startActivity(intent);
  }


  public void doNothing(View view) {
  }


  public void pressMinus(View view) {
    TextView tv = null;
    String spName = null;
    displayFavouriteWebsites();
    switch (this.numFavs) {
      case 3:
        tv = findViewById(R.id.textThirdFav);
        spName = "buttonWeb3";
        this.numFavs -= 1;
        break;
      case 2:
        tv = findViewById(R.id.textSecondFav);
        spName = "buttonWeb2";
        this.numFavs -= 1;
        break;
      case 1:
        tv = findViewById(R.id.textThirdFav);
        spName = "buttonWeb1";
        this.numFavs -= 1;
        break;
    }
    if (tv != null) {
      SharedPreferences sp1 = getSharedPreferences(spName, MODE_PRIVATE);
      SharedPreferences.Editor editor = sp1.edit();
      editor.clear();
      editor.apply();
      tv.setText(R.string.add_fav_website);
    }
    displayFavouriteWebsites();
  }

  public void pressPlus(View view) {
    displayFavouriteWebsites();
    switch (this.numFavs) {
      case 0:
        this.selectedButton = 1;
        this.numFavs += 1;
        pickFromWebsiteList("buttonWeb1");
        this.finish();
        //pickWeb();
        break;
      case 1:
        this.selectedButton = 2;
        this.numFavs += 1;
        pickFromWebsiteList("buttonWeb2");
        this.finish();
        //pickWeb();
        break;
      case 2:
        this.selectedButton = 3;
        this.numFavs += 1;
        pickFromWebsiteList("buttonWeb3");
        this.finish();
        //pickWeb();
        break;
    }
  }


  public void pressFav(View view) {
    TextView tv;
    String spName;
    switch (view.getId()) {
      case R.id.textFirstFav:
        tv = findViewById(R.id.textFirstFav);
        spName = "buttonWeb1";
        this.selectedButton = 1;
        break;
      case R.id.textSecondFav:
        tv = findViewById(R.id.textSecondFav);
        spName = "buttonWeb2";
        this.selectedButton = 2;
        break;
      case R.id.textThirdFav:
        tv = findViewById(R.id.textThirdFav);
        spName = "buttonWeb3";
        this.selectedButton = 3;
        break;
      default:
        tv = findViewById(R.id.textPickContact);
        spName = "buttonWeb4";
        this.selectedButton = 4;
    }
  }


  public void displayFavouriteWebsites() {
    int numFavs = 0;
    SharedPreferences sp1 = getSharedPreferences("buttonWeb1", Context.MODE_PRIVATE);
    if (sp1.contains("webName")) {

      String s1 = sp1.getString("webName", "");
      String s2 = sp1.getString("webLogo", "");

      TextView tv1 = findViewById(R.id.textFirstFav);
      tv1.setText(s1);

      ImageView iv1 = findViewById(R.id.iv1);
      iv1.setImageResource(Integer.valueOf(s2));

      numFavs += 1;
    }

    SharedPreferences sp2 = getSharedPreferences("buttonWeb2", Context.MODE_PRIVATE);
    if (sp2.contains("webName")) {

      String s1 = sp2.getString("webName", "");
      String s2 = sp2.getString("webLogo", "");

      TextView tv2 = findViewById(R.id.textSecondFav);
      tv2.setText(s1);

      ImageView iv2 = findViewById(R.id.iv2);
      iv2.setImageResource(Integer.valueOf(s2));


      numFavs += 1;
    }

    SharedPreferences sp3 = getSharedPreferences("buttonWeb3", Context.MODE_PRIVATE);
    if (sp3.contains("webName")) {

      String s1 = sp3.getString("webName", "");
      String s2 = sp3.getString("webLogo", "");

      TextView tv3 = findViewById(R.id.textThirdFav);
      tv3.setText(s1);

      ImageView iv3 = findViewById(R.id.iv3);
      iv3.setImageResource(Integer.valueOf(s2));


      numFavs += 1;
    }

    this.numFavs = numFavs;
    setWhiteBlocks();
  }
  public void goToPrevPage(View v) {
    startActivity(new Intent(this, FirstWizardScreen.class));
    finish();
  }

  public void goToNextPage(View v) {
    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
    launchesOnlyOnce.setPosition(DONE_WIZARD);
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  public void onBackPressed() {}

}

