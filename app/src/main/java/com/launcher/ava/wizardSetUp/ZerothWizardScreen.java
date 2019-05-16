package com.launcher.ava.wizardSetUp;

import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.ZERO_WIZARD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.launcher.ava.elderlylauncher.R;

public class ZerothWizardScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zeroth_wizard_screen);

    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(this);
    launchesOnlyOnce.setPosition(ZERO_WIZARD);
  }

  public void goToNextPage(View v) {
    startActivity(new Intent(this, FirstWizardScreen.class));
    finish();
  }

  public void onBackPressed() {}
}
