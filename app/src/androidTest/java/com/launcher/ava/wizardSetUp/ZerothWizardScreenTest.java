package com.launcher.ava.wizardSetUp;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.elderlylauncher.R;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class ZerothWizardScreenTest {

  public ActivityTestRule<ZerothWizardScreen> activityRule = new ActivityTestRule<>(ZerothWizardScreen.class);
  private ZerothWizardScreen zerothWizardScreen;


  @Before
  public void init(){
    activityRule.launchActivity(null);
    zerothWizardScreen = activityRule.getActivity();
  }

  @After
  public void and(){
    activityRule.finishActivity();
  }

  @Test
  public void onCreateTest() {
    View view = zerothWizardScreen.findViewById(R.id.textPhone);
    assertNotNull(view);
  }

  @Test
  public void goToNextPage() {
    onView(withId(R.id.nextBtn)).perform(click());
    onView(withId(R.id.prevButton))
      .check(matches(isDisplayed()));

  }

  @Test
  public void onBackPressed() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }
}