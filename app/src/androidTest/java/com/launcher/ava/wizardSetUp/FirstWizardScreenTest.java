package com.launcher.ava.wizardSetUp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.OnItemClick;
import com.launcher.ava.utilities.RecyclerList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.*;

public class FirstWizardScreenTest {

  public ActivityTestRule<FirstWizardScreen> activityRule = new ActivityTestRule<>(FirstWizardScreen.class);
  private FirstWizardScreen firstWizardScreen;
  private LaunchesOnlyOnce launchesOnlyOnce;

  @Before
  public void init(){
    activityRule.launchActivity(null);
    firstWizardScreen = activityRule.getActivity();
    launchesOnlyOnce = new LaunchesOnlyOnce(firstWizardScreen.getBaseContext());
  }


  @After
  public void and(){
    activityRule.finishActivity();
  }

  @Test
  public void onCreateTest() {
    View view = firstWizardScreen.findViewById(R.id.cLayoutBtn);
    assertNotNull(view);
    view = firstWizardScreen.findViewById(R.id.cLayoutWhiteBlock);
    assertNotNull(view);
    view = firstWizardScreen.findViewById(R.id.add_button);
    assertNotNull(view);
    view = firstWizardScreen.findViewById(R.id.remove_button);
    assertNotNull(view);

    assertEquals(launchesOnlyOnce.getPosition(), 1);

  }

  @Test
  public void setWhiteBlocksTest() {
    SharedPreferences sharedPreferences = firstWizardScreen.getSharedPreferences("button1", Context.MODE_PRIVATE);
    ConstraintLayout.LayoutParams paramsPlus = (ConstraintLayout.LayoutParams) firstWizardScreen.plusBtn.getLayoutParams();
    ConstraintLayout.LayoutParams paramsMinus = (ConstraintLayout.LayoutParams) firstWizardScreen.minusBtn.getLayoutParams();
    assertEquals(paramsPlus.startToStart,(R.id.firstPhoneScreenVertical6));
    assertEquals(paramsPlus.endToStart, R.id.firstPhoneScreenVertical5);


  }

  @Test
  public void doNothingTest() {
    onView(isRoot()).perform(ViewActions.pressBack());
    onView(withId(R.id.textPhone))
      .check(matches(isDisplayed()));
  }


  @Test
  public void openDialerTest() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:"));

    assertTrue(hasAction(Intent.ACTION_DIAL).matches(intent));
    assertTrue(hasData(Uri.parse("tel:")).matches(intent));
  }

  @Test
  public void putContactInfoInSharedPrefsTest() {
  }

  @Test
  public void pressMinus() {
  }

  @Test
  public void pressPlus() {
  }

  @Test
  public void passExtraInfoToNewIntent() {
  }

  @Test
  public void pickFromList() {
  }

  @Test
  public void pressFav() {
  }

  @Test
  public void onActivityResult() {
  }

  @Test
  public void displayFavouriteContacts() {
  }

  @Test
  public void onRequestPermissionsResult() {
  }

  @Test
  public void goToPrevPage() {
    onView(withId(R.id.prevButton)).perform(click());
    onView(withText("Hello, \n Please follow the next two screens to set up your favourite contacts and websites for quick access."))
      .check(matches(isDisplayed()));
  }

  @Test
  public void goToNextPage() {
    onView(withId(R.id.nextBtn)).perform(click());
    onView(withText(R.string.add_website_to_favourites))
      .check(matches(isDisplayed()));
  }

  @Test
  public void onBackPressed() {
  }
}