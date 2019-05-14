package com.launcher.ava.utilities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.launcher.ava.elderlylauncher.AppDrawer;
import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class RecyclerListTest {

  public ActivityTestRule<RecyclerList> activityRule = new ActivityTestRule<>(RecyclerList.class);
  private RecyclerList recyclerList;

  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);
    recyclerList = activityRule.getActivity();
  }

  @Test
  public void onCreateTest() {
    View view = recyclerList.findViewById(R.id.appList);
    assertNotNull(view);

  }

}