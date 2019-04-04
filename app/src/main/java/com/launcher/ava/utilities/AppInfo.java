package com.launcher.ava.utilities;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {

  public CharSequence label;
  public CharSequence packageName;
  public Drawable icon;

  @Override
  public int compareTo(AppInfo o) {

    for (int i = 0; i < label.length(); i++) {

      CharSequence l1 = this.label.toString().toLowerCase();
      CharSequence l2 = o.label.toString().toLowerCase();

      if (i >= o.label.length()) {
        return 1;
      }
      if (l1.charAt(i) > l2.charAt(i)) {
        return 1;
      } else if (l1.charAt(i) < l2.charAt(i)) {
        return -1;
      }
    }
    return 1;
  }
}

