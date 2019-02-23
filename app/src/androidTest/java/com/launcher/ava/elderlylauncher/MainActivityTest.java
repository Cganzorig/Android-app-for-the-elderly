package com.launcher.ava.elderlylauncher;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.launcher.ava.frequentlyUsedAppsScreen.AppFrequencyList;
import org.junit.Before;
import org.junit.Test;


// Only required if you mix JUnit 3 and 4
// @RunWith(AndroidJUnit4.class)

@SmallTest
public class MainActivityTest {

  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  MainActivity mainActivity;

  AppFrequencyList mockedAppFrequencyList = mock(AppFrequencyList.class);


  @Before
  public void createMainActivity() {
    activityRule.launchActivity(null);

    mainActivity = activityRule.getActivity();
  }

  @Test
  public void clickOnCreateShouldOpenMainActivityLayout() {

    View view = mainActivity.findViewById(R.id.frameLayout);
    assertNotNull(view);

  }

  @Test
  public void clickOnCreateShouldPopulateAppFrequencyList() {

    mockedAppFrequencyList.populate(mainActivity);
  }

  @Test
  public void clickLaunchHelperAppOpensActivityMainApp() {
  }

  @Test
  public void launchFrequentlyUsedAppList() {
  }

  @Test
  public void launchAppDrawer() {
  }

  @Test
  public void setFontSize() {
  }

  @Test
  public void onBackPressed() {
  }
}