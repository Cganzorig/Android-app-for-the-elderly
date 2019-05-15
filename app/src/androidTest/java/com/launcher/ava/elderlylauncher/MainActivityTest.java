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

  @Rule
  public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
  @Rule
  public ActivityTestRule<FirstWizardScreen> activityTestRule2 = new ActivityTestRule<>(FirstWizardScreen.class);

  public MainActivity mainActivity;
  private FirstWizardScreen firstWizardScreen;

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


  @After
  public void end() {
    firstWizardScreen = activityTestRule2.getActivity();
    LaunchesOnlyOnce launchesOnlyOnce = new LaunchesOnlyOnce(firstWizardScreen.getBaseContext());
    launchesOnlyOnce.setPosition(0);


    activityTestRule.finishActivity();
    activityTestRule2.finishActivity();
  }


  @Test
  public void onCreateLoadsCorrectView() {
    View view = mainActivity.findViewById(R.id.constraintLayout);
    assertNotNull(view);
  }

  @Test
  public void launchAppScreen() {
    onView(withId(R.id.cLayoutApp)).perform(click());
    onView(withText(R.string.main_apps)).check(matches(isDisplayed()));
  }

  @Test
  public void launchMessagesScreen() {
    onView(withId(R.id.cLayoutMessages)).perform(click());
    onView(withText(R.string.main_message)).check(matches(isDisplayed()));
  }

  @Test
  public void launchPhoneScreen() {
    onView(withId(R.id.cLayoutPhone)).perform(click());
    onView(withText(R.string.main_call)).check(matches(isDisplayed()));
  }

  @Test
  public void launchToolScreen() {
    onView(withId(R.id.cLayoutTools)).perform(click());
    onView(withText(R.string.main_question)).check(matches(isDisplayed()));
  }

  @Test
  public void launchInternetScreen() {
    onView(withId(R.id.cLayoutInternet)).perform(click());
    onView(withText(R.string.main_internet)).check(matches(isDisplayed()));
  }


  @Test
  public void doNothingTest() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }


}