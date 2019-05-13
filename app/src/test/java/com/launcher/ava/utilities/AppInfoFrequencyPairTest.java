package com.launcher.ava.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppInfoFrequencyPairTest {

  private AppInfo appInfo;
  private AppInfoFrequencyPair appInfoFrequencyPair;

  @Before
  public void setUpTest() {
    appInfo = new AppInfo();
    appInfo.label = "Hello";
    appInfo.packageName = "com.test.package";
    appInfo.icon = null;
    appInfoFrequencyPair = new AppInfoFrequencyPair(appInfo);
  }

  @Test
  public void testGetFreqTest() {
   Integer result = appInfoFrequencyPair.getFreq();
   Integer expected = 0;
   Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetLabelTest() {
    CharSequence cs = "Hello";
    assertEquals(appInfoFrequencyPair.getLabel(), cs);
  }

  @Test
  public void testGetIconTest() {
    assertNull(appInfoFrequencyPair.getIcon());
  }

  @Test
  public void incrementFreqTest() {
    appInfoFrequencyPair.incrementFreq();
    Integer expected = 1;
    Assert.assertEquals(appInfoFrequencyPair.getFreq(), expected);
  }

  @Test
  public void compareToTest() {
    AppInfoFrequencyPair appInfoFrequencyPair2 = new AppInfoFrequencyPair(appInfo);
    Assert.assertEquals(appInfoFrequencyPair2.compareTo(appInfoFrequencyPair), 0);
    appInfoFrequencyPair2.incrementFreq();
    Assert.assertEquals(appInfoFrequencyPair2.compareTo(appInfoFrequencyPair), 1);
    Assert.assertEquals(appInfoFrequencyPair.compareTo(appInfoFrequencyPair2), -1);
  }




}