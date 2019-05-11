package com.launcher.ava.wizardSetUp;

// https://www.supinfo.com/articles/single/3628-creer-ecran-introduction-votre-application-android

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class LaunchesOnlyOnce {

  public final static int ZERO_WIZARD = 0;
  public final static int ONE_WIZARD = 1;
  public final static int TWO_WIZARD = 2;
  public final static int DONE_WIZARD = 3;


  SharedPreferences.Editor editor;
  Context launchContext;
  SharedPreferences sp;


  public LaunchesOnlyOnce(Context context) {
    this.launchContext = context;
    this.sp = launchContext.getSharedPreferences("WIZARD", MODE_PRIVATE);
  }

  public void setPosition(int i) {
    SharedPreferences.Editor editor = sp.edit();
    editor.putInt("LOCATION", i);
    editor.apply();
  }

  public int getPosition() {
    return sp.getInt("LOCATION", 0);
  }

}
