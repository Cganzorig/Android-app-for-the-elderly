package com.launcher.ava.elderlylauncher;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FirstPhoneScreenTest {

  private FirstPhoneScreen firstPhoneScreen = new FirstPhoneScreen();


  @Test
  public void pressMinus() {

    int num = firstPhoneScreen.getNumFavs();
    onView(withId(R.id.remove_button)).perform(click());

    assertThat(firstPhoneScreen.getNumFavs(), is(num-1));

  }

  @Test
  public void pressPlus() {

    int num = firstPhoneScreen.getNumFavs();
    onView(withId(R.id.add_button)).perform(click());

    assertThat(firstPhoneScreen.getNumFavs(), is(num+1));

  }

}