package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.text.Layout;
import android.view.View;

import com.launcher.ava.utilities.ContactInfo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.junit.Assert.*;

public class SecondPhoneScreenTest {

  @Rule
  public IntentsTestRule<SecondPhoneScreen> activityRule = new IntentsTestRule<>(SecondPhoneScreen.class);

  private ContactInfo contactInfo = new ContactInfo();

  @Rule
  public ActivityTestRule<SecondPhoneScreen> activityTestRule = new ActivityTestRule<>(SecondPhoneScreen.class);
  public SecondPhoneScreen secondPhoneScreen;


  @Before
  public void createMainActivity() {
    activityTestRule.launchActivity(null);
    secondPhoneScreen = activityRule.getActivity();
  }

//  @Test
//  public void onCreate() {
//    View view = secondPhoneScreen.findViewById(R.id.cLayoutSecondPhoneScreenWhiteBlock);
//    assertNotNull(view);
//  }

//  @Test
//  public void makeCall() {
//    Intent intent = new Intent(Intent.ACTION_CALL);
//    intent.setData(Uri.parse("tel:" + "1234567890"));
//
//    assertTrue(hasAction(Intent.ACTION_CALL).matches(intent));
//    assertTrue(hasData(Uri.parse("tel:" + "1234567890")).matches(intent));
//  }

//  @Test
//  public void pressCall() {
//  }
}