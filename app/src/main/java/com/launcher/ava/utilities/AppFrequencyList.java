package com.launcher.ava.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppFrequencyList {

  private static ArrayList<AppInfoFrequencyPair> frequentlyUsedList = new ArrayList();
  private static AppFrequencyList instance = new AppFrequencyList();

  private AppFrequencyList() {
  }

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
      frequentlyUsedList.add(entry);
    }
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
    return frequentlyUsedList.get(num).getAppInfo();
  }

  public void removeApp(String package_name) {
    for (AppInfoFrequencyPair elem : frequentlyUsedList) {
      if (elem.getLabel().equals(package_name)) {
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
