package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.wizardSetUp.FirstWizardScreen;

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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v4.content.ContextCompat.startActivity;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

  @Rule
  public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<>(MainActivity.class, false, true);

  @Rule
  public ActivityTestRule<FirstWizardScreen> activityTestRule2 = new ActivityTestRule<>(FirstWizardScreen.class);

  public MainActivity mainActivity;
  public FirstWizardScreen firstWizardScreen;



  @Before
  public void setUp() {
    Intent intent2 = new Intent();
    activityTestRule2.launchActivity(intent2);
    firstWizardScreen = activityTestRule2.getActivity();

    Intent intent = new Intent();
    activityTestRule.launchActivity(intent);
    mainActivity = activityTestRule.getActivity();
  }

  @After
  public void close() {
    activityTestRule.finishActivity();
  }

  @Test
  public void onCreateLoadsCorrectView() {
    View view = mainActivity.findViewById(R.id.constraintLayout);
    assertNotNull(view);
  }

  @Test
  public void launchAppScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));

//    onView(withId(R.id.cLayoutApp)).perform(click());
//    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorAppScreen, 5000);
//
//    assertNotNull(appScreen);
//    appScreen.finish();
  }

  @Test
  public void launchMessagesScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutMessages));

//    onView(isRoot()).perform(ViewActions.pressBack());
//    onView(withId(R.id.cLayoutMessages)).check(matches(isDisplayed()));
//    assertNotNull(mainActivity.findViewById(R.id.cLayoutMessages));
//    onView(withId(R.id.cLayoutMessages)).perform(click());
//    Activity appScreen = getInstrumentation().waitForMonitorWithTimeout(monitorMessageScreen, 5000);
//
//    assertNotNull(appScreen);
//    appScreen.finish();
  }


  @Test
  public void launchPhoneScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutPhone));

//    assertNotNull(mainActivity.findViewById(R.id.cLayoutApp));
//    onView(withId(R.id.cLayoutApp)).perform(click());
//
//    Activity phone = getInstrumentation().waitForMonitorWithTimeout(monitorPhoneScreen, 5000);
//
//    assertNotNull(phone);
//    phone.finish();
  }


  @Test
  public void launchInternetScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutInternet));
//    assertNotNull(mainActivity.findViewById(R.id.cLayoutInternet));
//    onView(withId(R.id.cLayoutInternet)).perform(click());
//
//    Activity internet = getInstrumentation().waitForMonitorWithTimeout(monitorInternetScreen, 5000);
//
//    assertNotNull(internet);
//    internet.finish();
  }

  @Test
  public void launchCommonToolsScreen() {
    assertNotNull(mainActivity.findViewById(R.id.cLayoutTools));


//    onView(withContentDescription("WHAT DO YOU WANT TO DO?")).perform(click());
//    onView(withId(R.id.cLayoutTools)).perform(click());
//    onView(withText(endsWith("WHAT DO YOU WANT TO DO?"))).check(matches(isDisplayed()));
//
//    Activity commonTools = getInstrumentation().waitForMonitorWithTimeout(monitorCommonToolsScreen, 5000);
//
//    assertNotNull(commonTools);
//    commonTools.finish();
  }

  @Test
  public void doNothingTest() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }
}