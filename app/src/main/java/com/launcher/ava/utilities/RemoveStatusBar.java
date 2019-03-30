package com.launcher.ava.utilities;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class RemoveStatusBar {

  public static void remove(Activity a) {
    a.requestWindowFeature(Window.FEATURE_NO_TITLE);
    a.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }
}
