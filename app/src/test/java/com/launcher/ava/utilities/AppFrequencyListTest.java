package com.launcher.ava.utilities;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AppFrequencyListTest {

  private AppInfo appInfo = new AppInfo();
  private AppInfo appInfo2 = new AppInfo();
  private AppInfoFrequencyPair appInfoFrequencyPair = new AppInfoFrequencyPair(appInfo);
  private AppInfoFrequencyPair appInfoFrequencyPair2 = new AppInfoFrequencyPair(appInfo2);
  private AppFrequencyList appFrequencyList = AppFrequencyList.getInstance();


  @Test
  public void testGetInstance() {
    AppFrequencyList appFrequencyList2 = AppFrequencyList.getInstance();
    Assert.assertSame(appFrequencyList2, appFrequencyList);
  }

  @Test
  public void testAddNewApp() {
    int result = appFrequencyList.getSize();
    appFrequencyList.addNewApp(appInfo);
    int result1 = appFrequencyList.getSize();
    Assert.assertNotEquals(result,result1);
  }

  @Test
  public void testRemoveExistingApp() {
    appFrequencyList.addNewApp(appInfo);
    int result1 = appFrequencyList.getSize();
    appFrequencyList.removeApp(result1 - 1);
    int result2 = appFrequencyList.getSize();
    Assert.assertNotEquals(result1, result2);

  }

  @Test
  public void testRemoveNonExistingApp() {
    appFrequencyList.addNewApp(appInfo);
    int result1 = appFrequencyList.getSize();
    appFrequencyList.removeApp(5);
    int result2 = appFrequencyList.getSize();
    Assert.assertEquals(result1, result2);

  }
}