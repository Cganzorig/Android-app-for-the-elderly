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

public class MainActivityTest {

  @Rule
  public ActivityTestRule<FirstAppScreen> activityTestRule = new ActivityTestRule<>(FirstAppScreen.class);

  private MainActivity mainActivity = new MainActivity();

  Instrumentation.ActivityMonitor monitorAppScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
  Instrumentation.ActivityMonitor monitorPhoneScreen = getInstrumentation().addMonitor(FirstPhoneScreen.class.getName(), null, false);
  Instrumentation.ActivityMonitor monitorMessagesScreen = getInstrumentation().addMonitor(FirstMessagesScreen.class.getName(), null, false);
  Instrumentation.ActivityMonitor monitorInternetScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
  Instrumentation.ActivityMonitor monitorCommonToolsScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);


  @Test
  public void launchAppScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
    onView(withId(R.id.cLayoutApp)).perform(click());

    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorAppScreen, 5000);

    assertNotNull(appScreen);
    appScreen.finish();
  }

  @Test
  public void launchPhoneScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
    onView(withId(R.id.cLayoutApp)).perform(click());

    Activity phone = getInstrumentation().waitForMonitorWithTimeout(monitorPhoneScreen, 5000);

    assertNotNull(phone);
    phone.finish();
  }

  @Test
  public void launchMessagesScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutMessages));
    onView(withId(R.id.cLayoutMessages)).perform(click());

    Activity message = getInstrumentation().waitForMonitorWithTimeout(monitorMessagesScreen, 5000);

    assertNotNull(message);
    message.finish();
  }

  @Test
  public void launchInternetScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutInternet));
    onView(withId(R.id.cLayoutInternet)).perform(click());

    Activity internet = getInstrumentation().waitForMonitorWithTimeout(monitorInternetScreen, 5000);

    assertNotNull(internet);
    internet.finish();
  }

  @Test
  public void launchCommonToolsScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutTools));
    onView(withId(R.id.cLayoutTools)).perform(click());

    Activity commonTools = getInstrumentation().waitForMonitorWithTimeout(monitorCommonToolsScreen, 5000);

    assertNotNull(commonTools);
    commonTools.finish();
  }
}