package com.launcher.ava.frequentlyUsedAppsScreen;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;

import com.launcher.ava.frequentlyUsedAppsScreen.NameFrequencyPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppFrequencyList extends Application {
    private static  ArrayList<NameFrequencyPair> frequentlyUsedList = new ArrayList();

    public AppFrequencyList(Context c) {

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

    public static void incrementFrequency(String package_name) {
        for (NameFrequencyPair elem : frequentlyUsedList) {
            if (elem.getPackName().equals(package_name)) {
                elem.incrementFreq();
                return;
            }
        }
    }

    public void addNewApp(String package_name) {
        NameFrequencyPair newApp = new NameFrequencyPair(package_name);
        frequentlyUsedList.add(newApp);
    }

    public String topHit(){return frequentlyUsedList.get(0).getPackName();}

    public void removeApp(String package_name) {
        for (NameFrequencyPair elem : frequentlyUsedList) {
            if (elem.getPackName().equals(package_name)) {
                frequentlyUsedList.remove(elem);
                Collections.sort(frequentlyUsedList);
                return;
            }
        }
    }
}
