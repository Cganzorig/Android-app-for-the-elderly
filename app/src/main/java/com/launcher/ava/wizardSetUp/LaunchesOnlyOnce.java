package com.launcher.ava.wizardSetUp;

// https://www.supinfo.com/articles/single/3628-creer-ecran-introduction-votre-application-android
import android.content.Context;
import android.content.SharedPreferences;

public class LaunchesOnlyOnce {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context launchContext;

    int MODE_PRIVATE = 0;

    public LaunchesOnlyOnce(Context context){
        this.launchContext = context;
        preferences = launchContext.getSharedPreferences("LAUNCH_PREFERENCE", MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean isFirstTime(){
        return preferences.getBoolean("ISFIRSTTIME", true);
    }

    public void setFirstTime (boolean isFirstTime){
        editor.putBoolean("ISFIRSTTIME", isFirstTime);
        editor.commit();
    }

}
