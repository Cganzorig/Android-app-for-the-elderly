package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class FirstScreenCommonToolsTest {

  @Rule
  public IntentsTestRule<FirstScreenCommonTools> activityRule = new IntentsTestRule<>(FirstScreenCommonTools.class);

  @Test
  public void launchCreateContact() {
    Intent intent = new Intent(Intent.ACTION_INSERT,
      ContactsContract.Contacts.CONTENT_URI);
    onView(withId(R.id.cLayoutContact)).perform(click());
    assertTrue(hasAction(Intent.ACTION_INSERT).matches(intent));
    assertTrue(hasData(ContactsContract.Contacts.CONTENT_URI).matches(intent));
  }


//  @Test
//  public void launchDefaultCamera() {
//    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//    onView(withId(R.id.cLayoutPhoto)).perform(click());
//    assertTrue(hasData("android.media.action.IMAGE_CAPTURE").matches(intent));
//  }

  @Test
  public void launchSettings() {
    onView(withId(R.id.cLayoutSettings)).perform(click());
    intended(hasAction(equalTo(Settings.ACTION_SETTINGS)));
  }

  @Test
  public void launchAlarmSettings() {
    onView(withId(R.id.cLayoutAlarm)).perform(click());
    intended(hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)));
  }

//  @Test
//  public void launchPlayStore() {
//    onView(withId(R.id.installApp)).perform(click());
//    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
//    intended(hasAction(equalTo(AlarmClock.ACTION_SET_ALARM)));
//  }

}