package com.launcher.ava.utilities;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AppFrequencyList {

  private static ArrayList<AppInfoFrequencyPair> frequentlyUsedList = new ArrayList();
  private static AppFrequencyList instance = new AppFrequencyList();
  private static boolean populateInvokedForFirstTime = true;

  private AppFrequencyList() { }

  public static AppFrequencyList getInstance() {
    return instance;
  }

  public static void populate(Context c) {

    PackageManager pm = c.getPackageManager();
    Intent i = new Intent(Intent.ACTION_MAIN, null);
    i.addCategory(Intent.CATEGORY_LAUNCHER);

    List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
    for (ResolveInfo ri : allApps) {
      AppInfo app = new AppInfo();
      app.label = ri.loadLabel(pm);
      app.packageName = ri.activityInfo.packageName;
      app.icon = ri.activityInfo.loadIcon(pm);
      AppInfoFrequencyPair entry = new AppInfoFrequencyPair(app);
      SharedPreferences sp = c.getSharedPreferences("freqList", MODE_PRIVATE);
      String freqName = app.label.toString();
      if(sp.contains(freqName) && populateInvokedForFirstTime){
        int oldFreq = Integer.valueOf(Objects.requireNonNull(sp.getString(freqName, "")));
        entry.setFreq(oldFreq);
      }

      frequentlyUsedList.add(entry);
    }
    populateInvokedForFirstTime = false;
  }

  public void incrementFrequency(String package_name) {
    for (AppInfoFrequencyPair elem : frequentlyUsedList) {
      if (elem.getPackage().equals(package_name)) {
        elem.incrementFreq();
        Collections.sort(frequentlyUsedList, Collections.<AppInfoFrequencyPair>reverseOrder());
        return;
      }
    }
  }

  public void addNewApp(AppInfo app) {
    AppInfoFrequencyPair newApp = new AppInfoFrequencyPair(app);
    frequentlyUsedList.add(newApp);
  }

  public AppInfo getHit(int num) {
    Collections.sort(frequentlyUsedList, Collections.<AppInfoFrequencyPair>reverseOrder());
    return frequentlyUsedList.get(num).getAppInfo();
  }
  public int getHitFreq(int num) {
    return frequentlyUsedList.get(num).getFreq();
  }

  public AppInfoFrequencyPair getPairByPackName(String packName) {

    for (AppInfoFrequencyPair elem : frequentlyUsedList) {
      if (elem.getPackage().equals(packName)) {

        return elem;
      }
    }
    return null;
  }

  public void removeApp(String package_name) {
    for (AppInfoFrequencyPair elem : frequentlyUsedList) {
      if (elem.getPackage().equals(package_name)) {
        frequentlyUsedList.remove(elem);
        Collections.sort(frequentlyUsedList, Collections.<AppInfoFrequencyPair>reverseOrder());
        return;
      }
    }
  }

  // to make unit testing easier(so we don't have to mock insides of AppInfo)
  public void removeApp(int pos) {
    int i = 0;
    for (AppInfoFrequencyPair elem : frequentlyUsedList) {
      if (i == pos) {
        frequentlyUsedList.remove(elem);
        Collections.sort(frequentlyUsedList, Collections.<AppInfoFrequencyPair>reverseOrder());
        return;
      }
      i += 1;
    }
  }

  public int getSize() {
    return frequentlyUsedList.size();
  }
}
