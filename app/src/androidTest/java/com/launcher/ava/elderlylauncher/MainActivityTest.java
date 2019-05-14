package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.wizardSetUp.FirstWizardScreen;
import com.launcher.ava.wizardSetUp.LaunchesOnlyOnce;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.launcher.ava.wizardSetUp.LaunchesOnlyOnce.DONE_WIZARD;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

//  @Rule
//  public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

  @Rule
  public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
  @Rule
  public ActivityTestRule<FirstWizardScreen> activityTestRule2 = new ActivityTestRule<>(FirstWizardScreen.class);

  public MainActivity mainActivity;
  private FirstWizardScreen firstWizardScreen;


  Instrumentation.ActivityMonitor monitorAppScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
  Instrumentation.ActivityMonitor monitorMessageScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);


  @Before
  public void createMainActivity() {
    Intent intent1 = new Intent();
    activityTestRule2.launchActivity(intent1);
    firstWizardScreen = activityTestRule2.getActivity();
    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(firstWizardScreen.getBaseContext());
    launchesOnlyOnce.setPosition(3);

    Intent intent = new Intent();
    activityTestRule.launchActivity(intent);
    mainActivity = activityTestRule.getActivity();


  }


  @Test
  public void onCreateLoadsCorrectView() {
    View view = mainActivity.findViewById(R.id.constraintLayout);
    assertNotNull(view);
  }

  @Test
  public void launchAppScreen() {
    Instrumentation.ActivityMonitor monitorAppScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorMessageScreen = getInstrumentation().addMonitor(FirstAppScreen.class.getName(), null, false);


    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
    onView(withId(R.id.cLayoutApp)).perform(click());
    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorAppScreen, 5000);

    assertNotNull(appScreen);
    appScreen.finish();
  }

  @Test
  public void launchMessagesScreen() {
    onView(withId(R.id.cLayoutMessages)).perform(click());
    onView(withText(R.string.main_message)).check(matches(isDisplayed()));
//    assertNotNull(mainActivity.findViewById(R.id.cLayoutMessages));
//    onView(withId(R.id.cLayoutMessages)).perform(click());
//    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorMessageScreen, 5000);
//
//    assertNotNull(appScreen);
//    appScreen.finish();
  }


//    Assert.assertNotEquals(intent, mainActivity.getIntent());
//    onView(withId(R.id.RView)).check(matches(isDisplayed()));
//  @Before
//  public void setUp() {
//    Intents.init();
//    activityRule.launchActivity(new Intent());
//  }
//
//  @Test
//  public void launchAppScreen() {
//    onView(withId(R.id.cLayoutApp)).perform(click());
//    intended(hasComponent(FirstAppScreen.class.getName()));
//  }

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

  @Test
  public void doNothingTest() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }

  @Test
  public void buttonCall() {
    onView(withId(R.id.cLayoutBtn)).perform();
    onView(withText(R.string.pick_contact_from_list))
      .check(matches(isDisplayed()));
  }
}