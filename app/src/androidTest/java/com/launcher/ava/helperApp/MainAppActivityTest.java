package com.launcher.ava.helperApp;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

import android.app.Instrumentation;
import android.provider.ContactsContract;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.launcher.ava.elderlylauncher.R;
import org.junit.Before;
import org.junit.Test;

public class MainAppActivityTest {

  public ActivityTestRule<MainAppActivity> activityRule =
      new ActivityTestRule<>(MainAppActivity.class);

  MainAppActivity mainAppActivity;

  // caller app
  Instrumentation.ActivityMonitor monitorContacts = getInstrumentation()
      .addMonitor(ContactsContract.Contacts.CONTENT_URI.getClass().getName(),
          null, false);


  @Before
  public void createMainActivity() {

    activityRule.launchActivity(null);
    mainAppActivity = activityRule.getActivity();
  }

  @Test
  public void clickOnCreateShouldOpenMainAppActivityLayout() {
    View view = mainAppActivity.findViewById(R.id.mainAppLayout);
    assertNotNull(view);
  }

  @Test
  public void clickOnContactsLaunchesContacts() {
    Intents.init();
    // checks click is working
    Espresso.onView(ViewMatchers.withId(R.id.callButton)).perform(ViewActions.click());

  }

  @Test
  public void launchMessages() {

  }

  @Test
  public void launchChrome() {

  }

  @Test
  public void onActivityResult() {

  }
}