package com.launcher.ava.elderlylauncher;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ActivityScenario.ActivityAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RecyclerListTest {

  ActivityScenario<RecyclerList> scenario = ActivityScenario.launch(RecyclerList.class);

  @Test
  public void testOnCreateGetsCorrectLayout() {
    scenario.onActivity(new ActivityAction<RecyclerList>() {
      @Override
      public void perform(RecyclerList activity) {
        assertEquals(shadowOf(activity).getContentView().getId(), R.id.appList);
      }
    });
  }
}