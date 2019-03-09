package com.launcher.ava.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.launcher.ava.elderlylauncher.AppInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppInfoFrequencyPairTest {

  private AppInfo appInfo;
  private AppInfoFrequencyPair appInfoFrequencyPair;

  @Before
  public void setUp() {
    appInfo = new AppInfo();
    appInfo.label = "Hello";
    appInfo.packageName = "com.test.package";
    appInfo.icon = null;
    appInfoFrequencyPair = new AppInfoFrequencyPair(appInfo);
  }

  @Test
  public void testGetFreq() {
   Integer result = appInfoFrequencyPair.getFreq();
   Integer expected = 0;
   Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetLabel() {
    CharSequence cs = "Hello";
    assertEquals(appInfoFrequencyPair.getLabel(), cs);
  }

  @Test
  public void testGetIcon() {
    assertNull(appInfoFrequencyPair.getIcon());
  }

  @Test
  public void incrementFreq() {
    appInfoFrequencyPair.incrementFreq();
    Integer expected = 1;
    Assert.assertEquals(appInfoFrequencyPair.getFreq(), expected);
  }

  @Test
  public void compareTo() {
    AppInfoFrequencyPair appInfoFrequencyPair2 = new AppInfoFrequencyPair(appInfo);
    appInfoFrequencyPair2.incrementFreq();
    Assert.assertEquals(appInfoFrequencyPair2.compareTo(appInfoFrequencyPair), 1);
  }




}