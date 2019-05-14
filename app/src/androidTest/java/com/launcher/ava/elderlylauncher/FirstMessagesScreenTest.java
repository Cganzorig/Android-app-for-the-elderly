package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.wizardSetUp.FirstWizardScreen;
import com.launcher.ava.wizardSetUp.LaunchesOnlyOnce;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasCategories;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FirstMessagesScreenTest {


  @Rule
  public ActivityTestRule<FirstMessagesScreen> activityTestRule = new ActivityTestRule<>(FirstMessagesScreen.class);
  @Rule
  public ActivityTestRule<FirstWizardScreen> activityTestRule2 = new ActivityTestRule<>(FirstWizardScreen.class);

  private FirstMessagesScreen firstMessagesScreen;
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
    firstMessagesScreen = activityTestRule.getActivity();

  }

  @Test
  public void onCreateTest() {
    View view = firstMessagesScreen.findViewById(R.id.textMessages);
    TestCase.assertNotNull(view);
  }



  @Test
  public void openDialer() {
    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
    sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
    sendIntent.setData(Uri.parse("sms:"));
    sendIntent.putExtra("sms_body", "");

    assertTrue(hasAction(Intent.ACTION_VIEW).matches(sendIntent));
    assertTrue(hasData(Uri.parse("sms:")).matches(sendIntent));
  }

  @Test
  public void pickFromList() {
    Intent intent = new Intent(Intent.ACTION_PICK);
    assertTrue(hasAction(Intent.ACTION_PICK).matches(intent));

    onView(withId(R.id.textFirstFav)).perform(click());
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