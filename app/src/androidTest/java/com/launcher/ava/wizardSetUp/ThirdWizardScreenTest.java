package com.launcher.ava.wizardSetUp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class ThirdWizardScreenTest {

  public ActivityTestRule<ThirdWizardScreen> activityRule = new ActivityTestRule<>(ThirdWizardScreen.class);
  private ThirdWizardScreen thirdWizardScreen;


  @Before
  public void init(){
    activityRule.launchActivity(null);
    thirdWizardScreen = activityRule.getActivity();

  }
  @Test
  public void onCreate() {
    View view = thirdWizardScreen.findViewById(R.id.textPhone);
    assertNotNull(view);
  }

  @Test
  public void goToNextPage() {
    onView(withId(R.id.nextBtn)).perform(click());
    onView(withId(R.id.textApp))
      .check(matches(isDisplayed()));
  }
}