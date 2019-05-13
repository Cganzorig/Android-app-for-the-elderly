package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FirstPhoneScreenTest {

  @Rule
  public IntentsTestRule<FirstPhoneScreen> activityRule = new IntentsTestRule<>(FirstPhoneScreen.class);


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