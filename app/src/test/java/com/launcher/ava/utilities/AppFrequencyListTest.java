package com.launcher.ava.utilities;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AppFrequencyListTest {

  private AppInfo appInfo = new AppInfo();
  private AppInfo appInfo2 = new AppInfo();
  private AppInfoFrequencyPair appInfoFrequencyPair = new AppInfoFrequencyPair(appInfo);
  private AppFrequencyList appFrequencyList = AppFrequencyList.getInstance();

  @Before
  public void init(){
    appInfo.label = "Foo";
    appInfo.packageName="egg";
    appInfo2.label = "Bar";
    appInfo2.packageName="spam";

    appFrequencyList.addNewApp(appInfo);

    while (appFrequencyList.getSize() != 1){
      appFrequencyList.removeApp(0);
    }
  }

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
    appFrequencyList.removeApp(5);
    appFrequencyList.removeApp("YOLO");
    Assert.assertEquals(1, appFrequencyList.getSize());

  }

  @Test
  public void populate() {
  }

  @Test
  public void incrementFrequencyTest() {
    appFrequencyList.addNewApp(appInfo2);
    appFrequencyList.incrementFrequency("egg");
    appFrequencyList.incrementFrequency("");
    Assert.assertNotEquals(appFrequencyList.getHitFreq(0), appFrequencyList.getHitFreq(1));


  }

  @Test
  public void getHitTest() {
    Assert.assertEquals(appFrequencyList.getHit(0),appInfo);
  }

  @Test
  public void getHitFreqTest() {
    appFrequencyList.incrementFrequency("egg");
    Assert.assertEquals(appFrequencyList.getHitFreq(0),1);
  }

  @Test
  public void getPairByPackNameTest() {
    AppInfoFrequencyPair appInfoFrequencyPair2 = appFrequencyList.getPairByPackName("egg");
    AppInfoFrequencyPair appInfoFrequencyPair3 = appFrequencyList.getPairByPackName("YOLOOOO");
    Assert.assertEquals(appInfo,appInfoFrequencyPair2.getAppInfo());
    Assert.assertEquals(null, appInfoFrequencyPair3);
  }

  @Test
  public void removeAppTest() {
    appFrequencyList.addNewApp(appInfo2);
    appFrequencyList.removeApp("egg");
    Assert.assertEquals(1, appFrequencyList.getSize());
    appFrequencyList.addNewApp(appInfo);
    appFrequencyList.removeApp("egg");
    Assert.assertEquals(1, appFrequencyList.getSize());
  }

}