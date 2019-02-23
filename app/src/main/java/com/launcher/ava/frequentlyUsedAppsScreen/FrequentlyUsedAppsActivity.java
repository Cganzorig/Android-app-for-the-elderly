package com.launcher.ava.frequentlyUsedAppsScreen;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.utilities.BlurBuilder;
import com.launcher.ava.utilities.RemoveStatusBar;

public class FrequentlyUsedAppsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RemoveStatusBar.remove(this);
        setContentView(R.layout.activity_frequently_used_apps);


        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        final Drawable wallpaperDrawable = wallpaperManager.getDrawable();


        ConstraintLayout mContainerView = (ConstraintLayout) findViewById(R.id.container);
        Bitmap originalBitmap = BlurBuilder.drawableToBitmap(wallpaperDrawable);

        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );

        mContainerView.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        final TextView textApp1 = (TextView) findViewById(R.id.textApp1);
        textApp1.setText(AppFrequencyList.getInstance().getHit(0).label);
        final Button app1 = (Button) findViewById(R.id.app1);
        app1.setBackground(AppFrequencyList.getInstance().getHit(0).icon);

        final TextView textApp2 = (TextView) findViewById(R.id.textApp2);
        textApp2.setText(AppFrequencyList.getInstance().getHit(1).label);
        final Button app2 = (Button) findViewById(R.id.app2);
        app2.setBackground(AppFrequencyList.getInstance().getHit(1).icon);

        final TextView textApp3 = (TextView) findViewById(R.id.textApp3);
        textApp3.setText(AppFrequencyList.getInstance().getHit(2).label);
        final Button app3 = (Button) findViewById(R.id.app3);
        app3.setBackground(AppFrequencyList.getInstance().getHit(2).icon);

        final TextView textApp4 = (TextView) findViewById(R.id.textApp4);
        textApp4.setText(AppFrequencyList.getInstance().getHit(3).label);
        final Button app4 = (Button) findViewById(R.id.app4);
        app4.setBackground(AppFrequencyList.getInstance().getHit(3).icon);
    }

    public void onAppClick(View v) {

//        v.getBackground().setAlpha(100);

        switch(v.getId()) {
            case R.id.app1:
                startFreqApp(0);
                break;

            case R.id.app2:
                // Code for button 2 click
                startFreqApp(1);
                break;

            case R.id.app3:
                // Code for button 3 click
                startFreqApp(2);
                break;

            case R.id.app4:
                // Code for button 3 click
                startFreqApp(3);
                break;
        }

    }

    private void startFreqApp(int i){
        String packageName = AppFrequencyList.getInstance().getHit(i).packageName.toString();
        AppFrequencyList.getInstance().incrementFrequency(packageName);
        Intent launchIntent =
                getPackageManager().getLaunchIntentForPackage(packageName);
        startActivity(launchIntent);
    }

    public void onResetClick() {

    }

}
