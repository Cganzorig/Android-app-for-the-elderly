package com.launcher.ava.elderlylauncher;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.launcher.ava.utilities.BlurBuilder;
import com.launcher.ava.utilities.RemoveStatusBar;

public class AppDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RemoveStatusBar.remove(this);
        setContentView(R.layout.activity_app_drawer);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        final Drawable wallpaperDrawable = wallpaperManager.getDrawable();


        ConstraintLayout mContainerView = findViewById(R.id.appDrawerContainer);
        Bitmap originalBitmap = BlurBuilder.drawableToBitmap(wallpaperDrawable);

        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );

        mContainerView.setBackground(new BitmapDrawable(getResources(), blurredBitmap));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RView);
        com.launcher.ava.elderlylauncher.RAdapter radapter = new com.launcher.ava.elderlylauncher.RAdapter(this);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
