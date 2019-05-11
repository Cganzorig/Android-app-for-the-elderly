package com.launcher.ava.elderlylauncher;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.wizardSetUp.LaunchesOnlyOnce;
import com.launcher.ava.wizardSetUp.FirstWizardScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      AppFrequencyList.populate(this);
      LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
      launchesOnlyOnce.setFirstTime(true);
      if (launchesOnlyOnce.isFirstTime()) {
        launchWizardScreen();
      }
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      setFontSize();


    }

    public void launchAppScreen(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(this, FirstAppScreen.class);
        startActivity(intent);
    }

  public void launchPhoneScreen(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, FirstPhoneScreen.class);
    startActivity(intent);
  }

  public void launchMessagesScreen(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, FirstMessagesScreen.class);
    startActivity(intent);
  }

  public void launchInternetScreen(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, FirstInternetScreen.class);
    startActivity(intent);
  }

  public void launchCommonToolsScreen(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, FirstScreenCommonTools.class);
    startActivity(intent);
  }

  public void launchWizardScreen() {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, FirstWizardScreen.class);
    startActivity(intent);
  }

  public void setFontSize() {
    Configuration config = new Configuration();
    config.fontScale = 4.0f;
    getResources().getConfiguration().setTo(config);
  }

    @Override
    public void onBackPressed() {
        // do nothing when back button is pressed from home page
    }

}
