package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.View;

import com.launcher.ava.utilities.WebsiteDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class FirstInternetScreenTest {

  @Rule
  public IntentsTestRule<FirstInternetScreen> activityRule = new IntentsTestRule<>(FirstInternetScreen.class);


  private List websiteDatabase;
  @Before
  public void setUp() {
    websiteDatabase = WebsiteDatabase.getInstance();
  }


  @Test
  public void visitWebsite() {
    Uri webAddress = Uri.parse(WebsiteDatabase.getUrl("https://www.amazon.co.uk/"));

    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);
    onView(withId(R.id.textFirstFav)).perform(click());
    assertTrue(hasAction(Intent.ACTION_VIEW).matches(intent));
    assertTrue(hasData(webAddress).matches(intent));
  }


  @Test
  public void openBrowser() {
    String webName = "https://";
    Uri webAddress = Uri.parse(webName);

    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);
    onView(withId(R.id.buttonBrowser)).perform(click());
    assertTrue(hasAction(Intent.ACTION_VIEW).matches(intent));
    assertTrue(hasData(webAddress).matches(intent));
  }


}