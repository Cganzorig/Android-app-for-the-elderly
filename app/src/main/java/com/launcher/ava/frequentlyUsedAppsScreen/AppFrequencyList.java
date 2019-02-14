package com.launcher.ava.frequentlyUsedAppsScreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppFrequencyList {

    private static final ArrayList<NameFrequencyPair> frequentlyUsedList = new ArrayList();
    private static final AppFrequencyList instance = new AppFrequencyList();

    private AppFrequencyList(){}

    public static AppFrequencyList getInstance(){return instance;}

    public static void populate(Context c) {

        PackageManager pm = c.getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for(ResolveInfo ri:allApps) {
            String packageName = ri.activityInfo.packageName;
            NameFrequencyPair entry = new NameFrequencyPair(packageName);
            frequentlyUsedList.add(entry);
        }

        frequentlyUsedList.add(new NameFrequencyPair("TOP APP"));
    }

    public void sortFrequencyMap() {
        Collections.sort(frequentlyUsedList);
    }

    public void incrementFrequency(String package_name) {
        for (NameFrequencyPair elem : frequentlyUsedList) {
            if (elem.getPackName().equals(package_name)) {
                elem.incrementFreq();
                Collections.sort(frequentlyUsedList, Collections.<NameFrequencyPair>reverseOrder());
                return;
            }
        }
    }

    public void addNewApp(String package_name) {
        NameFrequencyPair newApp = new NameFrequencyPair(package_name);
        frequentlyUsedList.add(newApp);
    }

    public String getHit(int num){return frequentlyUsedList.get(num).getPackName();}

    public void removeApp(String package_name) {
        for (NameFrequencyPair elem : frequentlyUsedList) {
            if (elem.getPackName().equals(package_name)) {
                frequentlyUsedList.remove(elem);
                Collections.sort(frequentlyUsedList, Collections.<NameFrequencyPair>reverseOrder());
                return;
            }
        }
    }
}
