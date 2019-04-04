package com.launcher.ava.utilities;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {

  public CharSequence label;
  public CharSequence packageName;
  public Drawable icon;

  @Override
  public int compareTo(AppInfo o) {
    return this.label.charAt(0) >= o.label.charAt(0) ? 1 : -1;
//    for (int i = 0; i < label.length(); i++) {
//      if (this.label.charAt(i) > o.label.charAt(i)) {
//        return 1;
//      } else if (this.label.charAt(i) < o.label.charAt(i)) {
//        return -1;
//      }
//    }
//    return 1;
  }
}

