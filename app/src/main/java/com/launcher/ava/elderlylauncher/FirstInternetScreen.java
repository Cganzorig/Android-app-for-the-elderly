package com.launcher.ava.elderlylauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstInternetScreen extends AppCompatActivity {
  private int numWebs;
  private int selectedButton;

  ConstraintLayout addAndRemove;
  ConstraintLayout whiteBlock;
  Button plusBtn;
  Button minusBtn;
  EditText et;
  Button save;
  Button google;
  TextView plusMinusExplain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_internet_screen);

    this.addAndRemove = findViewById(R.id.cLayoutMessagesBtn);
    this.whiteBlock = findViewById(R.id.cLayoutMessagesWhiteBlock);
    this.plusBtn = findViewById(R.id.internet_add_button);
    this.minusBtn = findViewById(R.id.internet_remove_button);
    this.et = findViewById(R.id.editText);
    this.save = findViewById(R.id.saveButton);
    this.google = findViewById(R.id.buttonGoogle);
    this.plusMinusExplain = findViewById(R.id.textInternetExplainPlusMinus);

    displayFavouriteWebsites();
  }
  public void setWhiteBlocks() {
    ConstraintLayout.LayoutParams paramsAddAndRemove = (ConstraintLayout.LayoutParams) this.addAndRemove.getLayoutParams();
    ConstraintLayout.LayoutParams paramsWhiteBlock = (ConstraintLayout.LayoutParams) this.whiteBlock.getLayoutParams();
    TextView tv = findViewById(R.id.textInternetExplainPlusMinus);
    ConstraintLayout.LayoutParams paramsPlus = (ConstraintLayout.LayoutParams) this.plusBtn.getLayoutParams();
    ConstraintLayout.LayoutParams paramsMinus = (ConstraintLayout.LayoutParams) this.minusBtn.getLayoutParams();


    switch (this.numWebs) {
      case 0:
        paramsAddAndRemove.topToTop = R.id.firstWebScreenguide1;
        paramsAddAndRemove.bottomToTop = R.id.firstMessagesScreenguide2;
        paramsWhiteBlock.topToTop = R.id.firstMessagesScreenguide2;
        paramsWhiteBlock.bottomToTop = R.id.firstMessagesScreenguide6;

        minusBtn.setVisibility(View.INVISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_fav_website);

        paramsPlus.startToStart = R.id.firstMessagesScreenVertical5;
        paramsPlus.endToStart = R.id.firstMessagesScreenVertical6;
        break;
      case 1:
        paramsAddAndRemove.topToTop = R.id.firstMessagesScreenguide2;
        paramsAddAndRemove.bottomToTop = R.id.firstMessagesScreenguide3;
        paramsWhiteBlock.topToTop = R.id.firstMessagesScreenguide3;
        paramsWhiteBlock.bottomToTop = R.id.firstMessagesScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav_website);

        paramsPlus.startToStart = R.id.firstMessagesScreenVertical1;
        paramsPlus.endToStart = R.id.firstMessagesScreenVertical2;
        paramsMinus.startToStart = R.id.firstMessagesScreenVertical3;
        paramsMinus.endToStart = R.id.firstMessagesScreenVertical4;
        break;
      case 2:
        paramsAddAndRemove.topToTop = R.id.firstMessagesScreenguide3;
        paramsAddAndRemove.bottomToTop = R.id.firstMessagesScreenguide4;
        paramsWhiteBlock.topToTop = R.id.firstMessagesScreenguide4;
        paramsWhiteBlock.bottomToTop = R.id.firstMessagesScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav_website);

        paramsPlus.startToStart = R.id.firstMessagesScreenVertical1;
        paramsPlus.endToStart = R.id.firstMessagesScreenVertical2;
        paramsMinus.startToStart = R.id.firstMessagesScreenVertical3;
        paramsMinus.endToStart = R.id.firstMessagesScreenVertical4;
        break;
      case 3:
        paramsAddAndRemove.topToTop = R.id.firstMessagesScreenguide4;
        paramsAddAndRemove.bottomToTop = R.id.firstMessagesScreenguide5;
        paramsWhiteBlock.topToTop = R.id.firstMessagesScreenguide5;
        paramsWhiteBlock.bottomToTop = R.id.firstMessagesScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.INVISIBLE);
        tv.setText(R.string.remove_fav_website);

        paramsMinus.startToStart = R.id.firstMessagesScreenVertical5;
        paramsMinus.endToStart = R.id.firstMessagesScreenVertical6;
        break;

    }
    addAndRemove.setLayoutParams(paramsAddAndRemove);
    whiteBlock.setLayoutParams(paramsWhiteBlock);
    plusBtn.setLayoutParams(paramsPlus);
    minusBtn.setLayoutParams(paramsMinus);
  }

  public void doNothing(View view) {
  }

  public void putWebInfoInSharedPrefs(String sharedPrefName, String webName) {
    SharedPreferences sp = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("webName", webName);
    editor.apply();
  }

  public void pressMinus(View view) {
    TextView tv = null;
    String spName = null;
    displayFavouriteWebsites();
    switch (this.numWebs) {
      case 3:
        tv = findViewById(R.id.buttonInternetThirdFav);
        spName = "buttonWeb3";
        this.numWebs -= 1;
        break;
      case 2:
        tv = findViewById(R.id.buttonInternetSecondFav);
        spName = "buttonWeb2";
        this.numWebs -= 1;
        break;
      case 1:
        tv = findViewById(R.id.buttonInternetFirstFav);
        spName = "buttonWeb1";
        this.numWebs -= 1;
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
    switch (this.numWebs) {
      case 0:
        this.selectedButton = 1;
        this.numWebs += 1;
        pickWeb();
        break;
      case 1:
        this.selectedButton = 2;
        this.numWebs += 1;
        pickWeb();
        break;
      case 2:
        this.selectedButton = 3;
        this.numWebs += 1;
        pickWeb();
        break;
    }
  }

  public void saveButton(View view) {
    String name = et.getText().toString();

    switch (this.selectedButton) {
      case 1:
        putWebInfoInSharedPrefs("buttonWeb1", name);
        break;
      case 2:
        putWebInfoInSharedPrefs("buttonWeb2", name);
        break;
      case 3:
        putWebInfoInSharedPrefs("buttonWeb3", name);
        break;
    }

    //enable plus and minus buttons
    this.plusBtn.setEnabled(true);
    this.minusBtn.setEnabled(true);

    //make plus and minus buttons visible
    this.plusBtn.setVisibility(View.VISIBLE);
    this.minusBtn.setVisibility(View.VISIBLE);
    this.plusMinusExplain.setVisibility(View.VISIBLE);

    this.save.setVisibility(View.INVISIBLE);
    this.et.setVisibility(View.INVISIBLE);
    this.google.setVisibility(View.VISIBLE);
    et.onEditorAction(EditorInfo.IME_ACTION_DONE);
    et.setText(null);
    displayFavouriteWebsites();
  }

  public void pickWeb() {
    //disable plus and minus buttons
    this.plusBtn.setEnabled(false);
    this.minusBtn.setEnabled(false);
    this.plusMinusExplain.setVisibility(View.INVISIBLE);


    //make plus and minus buttons invisible
    this.plusBtn.setVisibility(View.INVISIBLE);
    this.minusBtn.setVisibility(View.INVISIBLE);

    this.google.setVisibility(View.INVISIBLE);
    this.save.setVisibility(View.VISIBLE);
    this.et.setVisibility(View.VISIBLE);
  }


  public void pressFav(View view) {
    TextView tv = null;
    String spName = "";
    switch (view.getId()) {
      case R.id.buttonInternetFirstFav:
        tv = findViewById(R.id.buttonInternetFirstFav);
        spName = "buttonWeb1";
        this.selectedButton = 1;
        break;
      case R.id. buttonInternetSecondFav:
        tv = findViewById(R.id.buttonInternetSecondFav);
        spName = "buttonWeb2";
        this.selectedButton = 2;
        break;
      case R.id.buttonInternetThirdFav:
        tv = findViewById(R.id.buttonInternetThirdFav);
        spName = "buttonWeb3";
        this.selectedButton = 3;
        break;
    }
    visitWebsite(view, spName);
  }

  public void visitWebsite(View view, String name) {
    SharedPreferences sp = getSharedPreferences(name, MODE_PRIVATE);
    String webName = sp.getString("webName","");

    webName = "https://www." + webName;
    Uri webAddress = Uri.parse(webName);

    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);

    if(intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  public void visitGoogle(View view) {
    String webName = "https://www.google.com";
    Uri webAddress = Uri.parse(webName);

    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);

    if(intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }

  public void displayFavouriteWebsites() {
    int numFavs = 0;
    SharedPreferences sp1 = getSharedPreferences("buttonWeb1", Context.MODE_PRIVATE);
    if (sp1.contains("webName")) {
      String s = sp1.getString("webName", "");
      TextView tv1 = findViewById(R.id.buttonInternetFirstFav);
      tv1.setText(s);
      numFavs += 1;
    }

    SharedPreferences sp2 = getSharedPreferences("buttonWeb2", Context.MODE_PRIVATE);
    if (sp2.contains("webName")) {
      String s = sp2.getString("webName", "");
      TextView tv2 = findViewById(R.id.buttonInternetSecondFav);
      tv2.setText(s);
      numFavs += 1;
    }

    SharedPreferences sp3 = getSharedPreferences("buttonWeb3", Context.MODE_PRIVATE);
    if (sp3.contains("webName")) {
      String s = sp3.getString("webName", "");
      TextView tv3 = findViewById(R.id.buttonInternetThirdFav);
      tv3.setText(s);
      numFavs += 1;
    }

    this.numWebs = numFavs;
    setWhiteBlocks();
  }
}
