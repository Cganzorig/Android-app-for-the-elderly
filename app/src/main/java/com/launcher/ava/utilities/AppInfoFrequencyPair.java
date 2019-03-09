package com.launcher.ava.utilities;

import android.graphics.drawable.Drawable;

import com.launcher.ava.elderlylauncher.AppInfo;

public class AppInfoFrequencyPair implements Comparable<AppInfoFrequencyPair>{

    private AppInfo app_info;
    private Integer freq;

    public AppInfoFrequencyPair(AppInfo app_info){
        this.app_info = app_info;
        this.freq = 0;
    }

    public Integer getFreq(){return this.freq;}
    public String getLabel(){return app_info.label.toString();}
    public Drawable getIcon(){return app_info.icon;}
    public CharSequence getPackage(){return app_info.packageName;}
    public AppInfo getAppInfo(){return app_info;}


    public void incrementFreq(){freq+=1;}

    @Override
    public int compareTo(AppInfoFrequencyPair o) {
        return (this.freq > o.getFreq()) ? 1: (this.freq==o.getFreq()) ? 0 : -1;
    }
}
