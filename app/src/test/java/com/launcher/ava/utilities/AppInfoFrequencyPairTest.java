package com.launcher.ava.utilities;

import com.launcher.ava.elderlylauncher.AppInfo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AppInfoFrequencyPairTest {


  AppInfo mockedAppInfo = Mockito.mock(AppInfo.class);

  AppInfoFrequencyPair appInfoFrequencyPair = new AppInfoFrequencyPair(mockedAppInfo);



  @Test
  public void testGetFreq() {
   Integer result = appInfoFrequencyPair.getFreq();
   Integer expected = 0;
   Assert.assertEquals(result, expected);
  }

  // Charles to find solution
//  @Test
//  public void testGetLabel() {
//    when(mockedAppInfo.label.toString()).thenReturn("Hello World!");
//    verify(mockedAppInfo, times(1)).label.toString();
//    appInfoFrequencyPair.getLabel();
//
//  }

  @Test
  public void incrementFreq() {
    appInfoFrequencyPair.incrementFreq();
    Integer expected = 1;
    Assert.assertEquals(appInfoFrequencyPair.getFreq(), expected);
  }

  @Test
  public void compareTo() {

    AppInfoFrequencyPair appInfoFrequencyPair2 = new AppInfoFrequencyPair(mockedAppInfo);
    appInfoFrequencyPair2.incrementFreq();
    Assert.assertEquals(appInfoFrequencyPair2.compareTo(appInfoFrequencyPair), 1);

  }




}