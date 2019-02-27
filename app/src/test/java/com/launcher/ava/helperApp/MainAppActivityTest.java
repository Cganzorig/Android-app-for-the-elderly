package com.launcher.ava.helperApp;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import com.launcher.ava.elderlylauncher.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


@RunWith(RobolectricTestRunner.class)
public class MainAppActivityTest {


  private MainAppActivity activity = Robolectric.setupActivity(MainAppActivity.class);

  @Test
  public void clickOnCreateShouldOpenMainAppActivityLayout() {
    assertEquals(R.id.mainAppLayout, shadowOf(activity).getContentView().getId());
  }

  @Test
  public void clickOnContactsLaunchesContacts() {
    activity.findViewById(R.id.callButton).performClick();
    Intent expectedIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
    assertEquals(expectedIntent.getComponent(), actual.getComponent());
  }

  @Test
  public void launchMessages() {
    activity.findViewById(R.id.messageButton).performClick();
    Intent expectedIntent = new Intent(Intent.ACTION_VIEW);
    expectedIntent.setData(Uri.parse("sms:"));
    Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
    assertEquals(expectedIntent.getComponent(), actual.getComponent());
  }

//  @Test
//  public void launchChrome() throws Exception {
//    activity.findViewById(R.id.searchButton).callOnClick();
//    ShadowActivity shadowActivity = Shadows.shadowOf(activity);
//  }

  @Test
  public void onActivityResult() {

  }
}