package com.launcher.ava.elderlylauncher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.launcher.ava.frequentlyUsedAppsScreen.FrequentlyUsedAppsActivity;
import com.launcher.ava.helperApp.MainAppActivity;
import com.launcher.ava.utilities.AppFrequencyList;
import org.junit.Before;
import org.junit.Test;

// Only required if you mix JUnit 3 and 4
// @RunWith(AndroidJUnit4.class)

@LargeTest
public class MainActivityTest {

  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
  private MainActivity mainActivity;

  // helper app
  Instrumentation.ActivityMonitor monitorHelperApp = getInstrumentation()
      .addMonitor(MainAppActivity.class.getName(), null, false);

  // drawer
  Instrumentation.ActivityMonitor monitorAppDrawer = getInstrumentation()
      .addMonitor(AppDrawer.class.getName(), null, false);

  // frequently used apps
  Instrumentation.ActivityMonitor monitorFrequentApps = getInstrumentation()
      .addMonitor(FrequentlyUsedAppsActivity.class.getName(), null, false);

  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);
    mainActivity = activityRule.getActivity();
  }

//  @Test
//  public void clickOnCreateShouldOpenMainActivityLayout() {
//
//    View view = mainActivity.findViewById(R.id.frameLayout);
//    assertNotNull(view);
//
//  }
//
//  @Test
//  public void clickOnCreateShouldPopulateAppFrequencyList() {
//    AppFrequencyList.populate(mainActivity);
//  }
//
//  @Test
//  public void clickLaunchHelperAppOpensActivityMainApp() {
//    // checks click is working
//    assertNotNull(mainActivity.findViewById(R.id.helperApp));
//
//    Espresso.onView(ViewMatchers.withId(R.id.helperApp)).perform(ViewActions.click());
//
//    Activity activity = getInstrumentation()
//        .waitForMonitorWithTimeout(monitorHelperApp, 5000);
//
//    assertNotNull(activity);
//    activity.finish();
//
//  }
//
//  @Test
//  public void launchFrequentlyUsedAppList() {
//    // checks click is working
//    assertNotNull(mainActivity.findViewById(R.id.frequentlyUsedButton));
//
//    Espresso.onView(ViewMatchers.withId(R.id.frequentlyUsedButton)).perform(ViewActions.click());
//
//    Activity activity = getInstrumentation()
//        .waitForMonitorWithTimeout(monitorFrequentApps, 5000);
//
//    assertNotNull(activity);
//    activity.finish();
//  }
//
//  @Test
//  public void launchAppDrawer() {
//    // checks click is working
//    assertNotNull(mainActivity.findViewById(R.id.app_drawer_button));
//
//    Espresso.onView(ViewMatchers.withId(R.id.app_drawer_button)).perform(ViewActions.click());
//
//    Activity activity = getInstrumentation()
//        .waitForMonitorWithTimeout(monitorAppDrawer, 5000);
//
//    assertNotNull(activity);
//    activity.finish();
//  }

}