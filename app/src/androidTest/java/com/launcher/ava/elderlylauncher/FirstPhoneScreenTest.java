package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.launcher.ava.utilities.ContactInfo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.junit.Assert.assertTrue;

public class FirstPhoneScreenTest {

  @Rule
  public IntentsTestRule<FirstPhoneScreen> activityRule = new IntentsTestRule<>(FirstPhoneScreen.class);

  public ContactInfo contactInfo;
//
//  @Before
//  public void setUp() {
//    contactInfo = new ContactInfo();
//    contactInfo.displayName = "John";
//    contactInfo.
//  }

  @Test
  public void openDialer() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:"));

    assertTrue(hasAction(Intent.ACTION_DIAL).matches(intent));
    assertTrue(hasData(Uri.parse("tel:")).matches(intent));
  }

  @Test
  public void pressMinus() {

//    int num = firstPhoneScreen.getNumFavs();
//    onView(withId(R.id.remove_button)).perform(click());
//
//    assertThat(firstPhoneScreen.getNumFavs(), is(num-1));

  }

  @Test
  public void pressPlus() {

//    int num = firstPhoneScreen.getNumFavs();
//    onView(withId(R.id.add_button)).perform(click());
//
//    assertThat(firstPhoneScreen.getNumFavs(), is(num+1));

  }

}