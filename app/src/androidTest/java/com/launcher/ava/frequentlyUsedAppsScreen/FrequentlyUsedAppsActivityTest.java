package com.launcher.ava.frequentlyUsedAppsScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import com.launcher.ava.elderlylauncher.MainActivity;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.AppFrequencyList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FrequentlyUsedAppsActivityTest {

  @Rule
  public IntentsTestRule<FrequentlyUsedAppsActivity> intentsRule =
      new IntentsTestRule<>(FrequentlyUsedAppsActivity.class);

  private String PACK_NAME_1;
  private String PACK_NAME_2;
  private String PACK_NAME_3;
  private String PACK_NAME_4;

  @Before
  public void setUp() {
    PACK_NAME_1 = AppFrequencyList.getInstance().getHit(0).packageName.toString();
    PACK_NAME_2 = AppFrequencyList.getInstance().getHit(1).packageName.toString();
    PACK_NAME_3 = AppFrequencyList.getInstance().getHit(2).packageName.toString();
    PACK_NAME_4 = AppFrequencyList.getInstance().getHit(3).packageName.toString();

  }

  @Test
  public void testClickingFirstButtonCreatesIntentForFirstApp() {
    onView(withId(R.id.app1)).perform(click());
    intended(toPackage(PACK_NAME_1));
  }

  @Test
  public void testClickingSecondButtonCreatesIntentForSecondApp() {
    onView(withId(R.id.app2)).perform(click());
    intended(toPackage(PACK_NAME_2));
  }

  @Test
  public void testClickingThirdButtonCreatesIntentForThirdApp() {
    onView(withId(R.id.app3)).perform(click());
    intended(toPackage(PACK_NAME_3));
  }

  @Test
  public void testClickingFourthButtonCreatesIntentForFourthApp() {
    onView(withId(R.id.app4)).perform(click());
    intended(toPackage(PACK_NAME_4));
  }
}