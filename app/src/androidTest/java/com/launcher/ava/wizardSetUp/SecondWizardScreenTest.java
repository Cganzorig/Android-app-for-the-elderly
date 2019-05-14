package com.launcher.ava.wizardSetUp;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class SecondWizardScreenTest {

  public ActivityTestRule<SecondWizardScreen> activityRule = new ActivityTestRule<>(SecondWizardScreen.class);
  private SecondWizardScreen SecondWizardScreen;

  @Before
  public void init(){
    activityRule.launchActivity(null);
    SecondWizardScreen = activityRule.getActivity();

  }
  @Test
  public void onCreate() {
    View view = SecondWizardScreen.findViewById(R.id.textPhone);
    assertNotNull(view);
  }

  @Test
  public void setWhiteBlocks() {
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
  public void pressFav() {
  }

  @Test
  public void displayFavouriteWebsites() {
  }

  @Test
  public void goToPrevPage() {
  }

  @Test
  public void goToNextPage() {
  }

  @Test
  public void onBackPressed() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }
}