package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class FirstAppScreenTest {

  @Rule
  public ActivityTestRule<FirstAppScreen> activityTestRule = new ActivityTestRule<>(FirstAppScreen.class);

  private FirstAppScreen firstAppScreen = null;

  Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AppDrawer.class.getName(), null, false);

  @Before
  public void setUp() throws Exception {
    firstAppScreen = activityTestRule.getActivity();

  }


  @Test
  public void deflateFakeSearchButton() {
  }

  @Test
  public void fakeToolbarPress() {
  }

  @Test
  public void setFavouriteApps() {
  }

  @Test
  public void onResume() {
  }

  @Test
  public void onAppClick() {
  }

  @Test
  public void onClickGoToAppDrawer() {
  }

  @Test
  public void onResetClick() {
  }

  @Test
  public void onCreateOptionsMenu() {
  }

  @Test
  public void launchPlayStore() {
  }

  @Test
  public void launchUninstallApp() {
  }
}
