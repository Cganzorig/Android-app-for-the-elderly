package com.launcher.ava.utilities;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {

  public CharSequence label;
  public CharSequence packageName;
  public Drawable icon;

  @Override
  public int compareTo(AppInfo o) {
    for (int i = 0; i < label.length(); i++) {
      if (i >= o.label.length()) {
        return 1;
      }
      if (Character.toLowerCase(this.label.charAt(i)) > Character.toLowerCase(o.label.charAt(i))) {
        return 1;
      } else if (Character.toLowerCase(this.label.charAt(i)) < Character.toLowerCase(o.label.charAt(i))) {
        return -1;
      }
    }
    return 1;
  }
}

