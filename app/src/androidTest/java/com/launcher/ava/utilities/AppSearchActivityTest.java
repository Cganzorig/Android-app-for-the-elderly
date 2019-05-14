package com.launcher.ava.utilities;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.launcher.ava.elderlylauncher.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

public class AppSearchActivityTest {

  public ActivityTestRule<AppSearchActivity> activityRule = new ActivityTestRule<>(AppSearchActivity.class);
  private AppSearchActivity appSearchActivity;

  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);
    appSearchActivity = activityRule.getActivity();
  }

  @After
  public void and(){
    activityRule.finishActivity();
  }

  @Test
  public void onCreateTest() {
    View view = appSearchActivity.getCurrentFocus();
    assertEquals(view, appSearchActivity.findViewById(R.layout.searchable_layout));
    appSearchActivity.finish();

  }

  @Test
  public void onNewIntentViewTest() {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    appSearchActivity.onNewIntent(intent);
    assertEquals(intent, appSearchActivity.getIntent());
    appSearchActivity.finish();
  }


}