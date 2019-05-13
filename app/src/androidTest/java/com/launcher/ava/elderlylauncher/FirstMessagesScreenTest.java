package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.launcher.ava.utilities.ContactInfo;

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

public class FirstMessagesScreenTest {

  private FirstMessagesScreen firstMessagesScreen = new FirstMessagesScreen();


  private ContactInfo contactInfo = new ContactInfo();

  @Rule
  public IntentsTestRule<FirstMessagesScreen> activityRule = new IntentsTestRule<>(FirstMessagesScreen.class);

//  @Test
//  public void pickFromList() {
//    Intent intent = new Intent(Intent.ACTION_PICK);
//    assertTrue(hasAction(Intent.ACTION_PICK).matches(intent));
//  }


//  @Test
//  public void pressMinus() {
//
////    int num = firstMessagesScreen.getNumFavs();
////    onView(withId(R.id.remove_button)).perform(click());
////
////    assertThat(firstMessagesScreen.getNumFavs(), is(num-1));
//
//  }
//
//  @Test
//  public void pressPlus() {
//
////    int num = firstMessagesScreen.getNumFavs();
////    onView(withId(R.id.add_button)).perform(click());
////
////    assertThat(firstMessagesScreen.getNumFavs(), is(num+1));
//
//  }



}