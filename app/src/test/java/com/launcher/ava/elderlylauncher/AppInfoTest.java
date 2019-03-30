package com.launcher.ava.elderlylauncher;

import static org.junit.Assert.assertEquals;

import com.launcher.ava.utilities.AppInfo;
import org.junit.Before;
import org.junit.Test;

public class AppInfoTest {

  private AppInfo appInfo1;
  private AppInfo appInfo2;

  @Before
  public void setUp() {
    appInfo1 = new AppInfo();
    appInfo1.label = "Hello";
    appInfo2 = new AppInfo();
    appInfo2.label = "HelloLonger";
  }


  @Test
  public void compareTo() {
    assertEquals( 1, appInfo1.compareTo(appInfo2));
  }
}