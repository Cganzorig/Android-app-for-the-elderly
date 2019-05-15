package com.launcher.ava.elderlylauncher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FirstAppScreenTest {

  @Rule
  public ActivityTestRule<FirstAppScreen> activityTestRule = new ActivityTestRule<>(FirstAppScreen.class);

  private FirstAppScreen firstAppScreen = null;

  @Before
  public void setUp() {
    firstAppScreen = activityTestRule.getActivity();
  }

}
