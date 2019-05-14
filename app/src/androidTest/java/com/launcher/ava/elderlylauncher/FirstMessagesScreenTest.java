package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.utilities.ContactInfo;

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


  private ContactInfo contactInfo = new ContactInfo();

  @Rule
  public ActivityTestRule<FirstMessagesScreen> activityRule = new ActivityTestRule<>(FirstMessagesScreen.class);

  private FirstMessagesScreen firstMessagesScreen = null;

  @Before
  public void Init() {
    activityRule.launchActivity(null);
    firstMessagesScreen = activityRule.getActivity();
//    firstMessagesScreen = new FirstMessagesScreen();
//    firstMessagesScreen.numFavs = 1;

  }

  @Test
  public void onCreateTest() {
    View view = firstMessagesScreen.findViewById(R.id.textPhone);
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
//
//  @Test
//  public void pickFromList() {
//    Intent intent = new Intent(Intent.ACTION_PICK);
//    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
//    assertTrue(hasAction(Intent.ACTION_PICK).matches(intent));
//  }

//  @Test
//  public void pressMinus() {
//    onView(withId(R.id.remove_button)).perform(click());
//    assertEquals(0, firstMessagesScreen.numFavs);
//

//    int num = firstMessagesScreen.getNumFavs();
//    onView(withId(R.id.remove_button)).perform(click());
//
//    assertThat(firstMessagesScreen.getNumFavs(), is(num-1));

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