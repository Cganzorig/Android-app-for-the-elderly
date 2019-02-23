package com.launcher.ava.elderlylauncher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.launcher.ava.frequentlyUsedAppsScreen.AppFrequencyList;
import com.launcher.ava.helperApp.MainAppActivity;
import org.junit.Before;
import org.junit.Test;


// Only required if you mix JUnit 3 and 4
// @RunWith(AndroidJUnit4.class)

@SmallTest
public class MainActivityTest {

  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  MainActivity mainActivity;

  AppFrequencyList mockedAppFrequencyList = mock(AppFrequencyList.class);

  Instrumentation.ActivityMonitor monitorHelperApp = getInstrumentation()
      .addMonitor(MainAppActivity.class.getName(), null, false);

  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);

    mainActivity = activityRule.getActivity();
  }

  @Test
  public void clickOnCreateShouldOpenMainActivityLayout() {

    View view = mainActivity.findViewById(R.id.frameLayout);
    assertNotNull(view);

  }

  @Test
  public void clickOnCreateShouldPopulateAppFrequencyList() {

    mockedAppFrequencyList.populate(mainActivity);
  }

  @Test
  public void clickLaunchHelperAppOpensActivityMainApp() {

    // checks click is working
    assertNotNull(mainActivity.findViewById(R.id.helperApp));

    onView(withId(R.id.helperApp)).perform(click());

    Activity mainAppActivity = getInstrumentation()
        .waitForMonitorWithTimeout(monitorHelperApp, 5000);

    assertNotNull(mainAppActivity);
    mainAppActivity.finish();


  }

  @Test
  public void launchFrequentlyUsedAppList() {
  }

  @Test
  public void launchAppDrawer() {
  }

  @Test
  public void setFontSize() {
  }

  @Test
  public void onBackPressed() {
  }
}