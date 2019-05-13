package com.launcher.ava.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AppFrequencyListTest {

  private AppInfo mockedAppInfo = Mockito.mock(AppInfo.class);
  private AppFrequencyList appFrequencyList = AppFrequencyList.getInstance();

  @Test
  public void testGetInstance() {
    AppFrequencyList appFrequencyList2 = AppFrequencyList.getInstance();
    Assert.assertSame(appFrequencyList2, appFrequencyList);
  }

  @Test
  public void testAddNewApp() {
    int result = appFrequencyList.getSize();
    appFrequencyList.addNewApp(mockedAppInfo);
    int result1 = appFrequencyList.getSize();
    Assert.assertNotEquals(result,result1);
  }

  @Test
  public void testRemoveExistingApp() {
    appFrequencyList.addNewApp(mockedAppInfo);
    int result1 = appFrequencyList.getSize();
    appFrequencyList.removeApp(result1 - 1);
    int result2 = appFrequencyList.getSize();
    Assert.assertNotEquals(result1, result2);

  }

  @Test
  public void testRemoveNonExistingApp() {
    appFrequencyList.addNewApp(mockedAppInfo);
    int result1 = appFrequencyList.getSize();
    appFrequencyList.removeApp(5);
    int result2 = appFrequencyList.getSize();
    Assert.assertEquals(result1, result2);

  }

  @Test
  public void populate() {

  }

  @Test
  public void incrementFrequency() {
  }

  @Test
  public void getHit() {
  }

  @Test
  public void getHitFreq() {
  }

  @Test
  public void getPairByPackName() {
  }

  @Test
  public void removeApp() {
  }

  @Test
  public void removeApp1() {
  }

  @Test
  public void getSize() {
  }
}