package com.launcher.ava.wizardSetUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.elderlylauncher.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class SecondWizardScreenTest {

  public ActivityTestRule<SecondWizardScreen> activityRule = new ActivityTestRule<>(SecondWizardScreen.class);
  private SecondWizardScreen secondWizardScreen;

  @Before
  public void init(){
    Intent intent = new Intent();
    activityRule.launchActivity(intent);
    secondWizardScreen = activityRule.getActivity();
  }

  @After
  public void and(){
    activityRule.finishActivity();
  }
  @Test
  public void onCreate() {
    View view = secondWizardScreen.findViewById(R.id.textPhone);
    assertNotNull(view);
  }

  @Test
  public void pickFromWebsiteList() {
  }

  @Test
  public void doNothing() {
  }

  @Test
  public void pressMinus() {
  }

  @Test
  public void pressPlus() {
  }


  @Test
  public void goToPrevPage() {
    onView(withId(R.id.prevButton)).perform(click());
    onView(withText(R.string.pick_three_favourite_contacts))
      .check(matches(isDisplayed()));
  }

  @Test
  public void goToNextPage() {
    onView(withId(R.id.nextBtn)).perform(click());
    onView(withText("MAKE AVA DEFAULT HOME APP"))
      .check(matches(isDisplayed()));
  }

  @Test
  public void onBackPressed() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }
}