package com.launcher.ava.helperApp;

import static org.junit.Assert.assertNotNull;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.launcher.ava.elderlylauncher.R;
import org.junit.Before;
import org.junit.Test;

public class MainAppActivityTest {

  public ActivityTestRule<MainAppActivity> activityRule = new ActivityTestRule<>(MainAppActivity.class);

  MainAppActivity mainAppActivity;

  @Before
  public void createMainActivity() {

    activityRule.launchActivity(null);
    mainAppActivity = activityRule.getActivity();
  }

  @Test
  public void clickOnCreateShouldOpenMainAppActivityLayout() {
    View view = mainAppActivity.findViewById(R.id.mainAppLayout);
    assertNotNull(view);
  }

  @Test
  public void launchContacts() {
  }

  @Test
  public void launchMessages() {
  }

  @Test
  public void launchChrome() {
  }

  @Test
  public void onActivityResult() {
  }
}