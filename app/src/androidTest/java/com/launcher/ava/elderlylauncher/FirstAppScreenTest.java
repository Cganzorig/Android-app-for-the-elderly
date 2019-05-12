package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class FirstAppScreenTest {

  @Rule
  public ActivityTestRule<FirstAppScreen> activityTestRule = new ActivityTestRule<>(FirstAppScreen.class);

  private FirstAppScreen firstAppScreen = activityTestRule.getActivity();

  Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AppDrawer.class.getName(), null, false);

  @Test
  public void launchPlayStore() {
    assertNotNull(firstAppScreen.findViewById(R.id.openDrawer));
    onView(withId(R.id.openDrawer)).perform(click());

    Activity appDrawer = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

    assertNotNull(appDrawer);
    appDrawer.finish();

  }

  @Test
  public void launchUninstallApp() {
    assertNotNull(firstAppScreen.findViewById(R.id.uninstallApp));
    onView(withId(R.id.uninstallApp)).perform(click());

    Activity appDrawer = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

    assertNotNull(appDrawer);
    appDrawer.finish();
  }

}