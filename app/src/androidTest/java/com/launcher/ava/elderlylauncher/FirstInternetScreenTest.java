package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.View;

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


public class FirstInternetScreenTest {

  private FirstInternetScreen firstInternetScreen = new FirstInternetScreen();

  @Rule
  public IntentsTestRule<FirstInternetScreen> activityRule = new IntentsTestRule<>(FirstInternetScreen.class);


//  @Test
//  public void visitWebsite() {
//    String webName = "https://";
//    Uri webAddress = Uri.parse(webName);
//
//    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);
//
//    assertTrue(hasAction(Intent.ACTION_VIEW).matches(intent));
//    assertTrue(hasData(webAddress).matches(intent));
//  }

//  public void openBrowser(View view) {
//
//    String webName = "https://";
//    Uri webAddress = Uri.parse(webName);
//
//    Intent intent = new Intent(Intent.ACTION_VIEW, webAddress);
//
//    if(intent.resolveActivity(getPackageManager()) != null) {
//      startActivity(intent);
//    }
//  }


//  @Test
//  public void pressMinus() {
//
////    int num = firstInternetScreen.getNumFavs();
////    onView(withId(R.id.remove_button)).perform(click());
////
////    assertThat(firstInternetScreen.getNumFavs(), is(num-1));
//
//  }
//
//  @Test
//  public void pressPlus() {
//
////    int num = firstInternetScreen.getNumFavs();
////    onView(withId(R.id.add_button)).perform(click());
////
////    assertThat(firstInternetScreen.getNumFavs(), is(num+1));
//
//  }

}