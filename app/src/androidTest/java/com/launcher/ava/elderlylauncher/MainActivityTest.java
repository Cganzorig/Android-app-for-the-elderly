package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  public MainActivity mainActivity;

  private Intent intent;

  @Before
  public void setUp() {
    activityRule.launchActivity(new Intent());
    mainActivity = activityRule.getActivity();
    intent = new Intent(mainActivity.getBaseContext(), FirstAppScreen.class);
  }

  @Test
  public void launchAppScreen() {
    mainActivity.launchAppScreen(mainActivity.findViewById(R.id.cLayoutApp));
    assertEquals(intent, mainActivity.getIntent());
  }

//
//  private MainActivity mainActivity = new MainActivity();
//
//  Instrumentation.ActivityMonitor monitorAppScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
//  Instrumentation.ActivityMonitor monitorPhoneScreen = getInstrumentation().addMonitor(FirstPhoneScreen.class.getName(), null, false);
//  Instrumentation.ActivityMonitor monitorMessagesScreen = getInstrumentation().addMonitor(FirstMessagesScreen.class.getName(), null, false);
//  Instrumentation.ActivityMonitor monitorInternetScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
//  Instrumentation.ActivityMonitor monitorCommonToolsScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
//
//
//  @Test
//  public void launchAppScreen() {
//    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
//    onView(withId(R.id.cLayoutApp)).perform(click());
//
//    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorAppScreen, 5000);
//
//    assertNotNull(appScreen);
//    appScreen.finish();
//  }
//
//  @Test
//  public void launchPhoneScreen() {
////    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
////    onView(withId(R.id.cLayoutApp)).perform(click());
////
////    Activity phone = getInstrumentation().waitForMonitorWithTimeout(monitorPhoneScreen, 5000);
////
////    assertNotNull(phone);
////    phone.finish();
//  }
//
//  @Test
//  public void launchMessagesScreen() {
////    assertNotNull(mainActivity.findViewById(R.id.cLayoutMessages));
////    onView(withId(R.id.cLayoutMessages)).perform(click());
////
////    Activity message = getInstrumentation().waitForMonitorWithTimeout(monitorMessagesScreen, 5000);
////
////    assertNotNull(message);
////    message.finish();
//  }
//
//  @Test
//  public void launchInternetScreen() {
////    assertNotNull(mainActivity.findViewById(R.id.cLayoutInternet));
////    onView(withId(R.id.cLayoutInternet)).perform(click());
////
////    Activity internet = getInstrumentation().waitForMonitorWithTimeout(monitorInternetScreen, 5000);
////
////    assertNotNull(internet);
////    internet.finish();
//  }
//
//  @Test
//  public void launchCommonToolsScreen() {
////    assertNotNull(mainActivity.findViewById(R.id.cLayoutTools));
////    onView(withId(R.id.cLayoutTools)).perform(click());
////
////    Activity commonTools = getInstrumentation().waitForMonitorWithTimeout(monitorCommonToolsScreen, 5000);
////
////    assertNotNull(commonTools);
////    commonTools.finish();
//  }
}