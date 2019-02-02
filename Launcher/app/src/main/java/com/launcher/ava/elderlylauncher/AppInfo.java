package com.launcher.ava.elderlylauncher;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {
    public CharSequence label;
    public CharSequence packageName;
    public Drawable icon;

    @Override
    public int compareTo(AppInfo o) {
        return this.label.charAt(0) >= o.label.charAt(0) ? 1 : -1;
    }
}

