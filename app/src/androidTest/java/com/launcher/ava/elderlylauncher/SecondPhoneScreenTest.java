package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.launcher.ava.utilities.ContactInfo;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.junit.Assert.*;

public class SecondPhoneScreenTest {

  @Rule
  public IntentsTestRule<SecondPhoneScreen> activityRule = new IntentsTestRule<>(SecondPhoneScreen.class);

  private ContactInfo contactInfo = new ContactInfo();

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