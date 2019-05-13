package com.launcher.ava.elderlylauncher;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class AppDrawerTest {

  public ActivityTestRule<AppDrawer> activityRule = new ActivityTestRule<>(AppDrawer.class);
  private AppDrawer appDrawer;


  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);
    appDrawer = activityRule.getActivity();
  }

  @Test
  public void onCreateLoadsCorrectView() {
    View view = appDrawer.findViewById(R.id.appDrawerContainer);
    assertNotNull(view);
  }

  @Test
  public void onCreateSetsRAdapter() {
    RecyclerView recyclerView = appDrawer.findViewById(R.id.RView);
    assertNotNull(recyclerView);
  }

}