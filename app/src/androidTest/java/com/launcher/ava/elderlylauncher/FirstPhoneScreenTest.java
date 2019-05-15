package com.launcher.ava.elderlylauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.wizardSetUp.FirstWizardScreen;
import com.launcher.ava.wizardSetUp.LaunchesOnlyOnce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstPhoneScreenTest {

  @Rule
  public ActivityTestRule<FirstPhoneScreen> activityTestRule = new ActivityTestRule<>(FirstPhoneScreen.class);
  @Rule
  public ActivityTestRule<FirstWizardScreen> activityTestRule2 = new ActivityTestRule<>(FirstWizardScreen.class);

  private FirstPhoneScreen firstPhoneScreen;
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
    firstPhoneScreen = activityTestRule.getActivity();

  }



  @Test
  public void pickFromList() {
    Intent intent = new Intent(Intent.ACTION_PICK);
    assertTrue(hasAction(Intent.ACTION_PICK).matches(intent));

    onView(withId(R.id.textFirstFav)).perform(click());
  }

  @Test
  public void openDialer() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:"));

    assertTrue(hasAction(Intent.ACTION_DIAL).matches(intent));
    assertTrue(hasData(Uri.parse("tel:")).matches(intent));
  }

  @Test
  public void pressMinus() {
    onView(withId(R.id.add_button)).perform(click());
  }

  @Test
  public void pressPlus() {
    onView(withId(R.id.add_button)).perform(click());

  }

  @Test
  public void pressFav() {
    onView(withId(R.id.textFirstFav)).perform(click());
  }

}