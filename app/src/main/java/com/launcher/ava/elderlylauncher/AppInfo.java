package com.launcher.ava.elderlylauncher;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {
    public CharSequence label;
    public CharSequence packageName;
    public Drawable icon;

    @Override
    public int compareTo(AppInfo o) {
        for(int i= 0; i < label.length(); i++){
            if(this.label.charAt(i) > o.label.charAt(i)) {
                return 1;
            } else if(this.label.charAt(i) < o.label.charAt(i)) {
                return -1;
            }
        }
        return 1;
    }
}

