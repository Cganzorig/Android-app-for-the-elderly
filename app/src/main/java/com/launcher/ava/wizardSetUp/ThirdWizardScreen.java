package com.launcher.ava.wizardSetUp;

import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.DONE_WIZARD;
import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.THREE_WIZARD;
import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.TWO_WIZARD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.launcher.ava.elderlylauncher.MainActivity;
import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;

public class ThirdWizardScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_third_wizard_screen);

    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
    launchesOnlyOnce.setPosition(THREE_WIZARD);
  }

  public void goToNextPage(View v) {
    Intent i = new Intent(Intent.ACTION_MAIN);
    i.addCategory(Intent.CATEGORY_HOME);
    startActivity(i);
    finish();
  }
}
