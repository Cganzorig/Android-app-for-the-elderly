package com.launcher.ava.utilities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AppInfoTest {

  private AppInfo appInfo = new AppInfo();
  private AppInfo appInfo2 = new AppInfo();
  private AppInfo appInfo3 = new AppInfo();
  private AppInfo appInfo4 = new AppInfo();
  private AppInfo appInfo5 = new AppInfo();

  @Before
  public void init(){
    appInfo = new AppInfo();
    appInfo2 = new AppInfo();
    appInfo3 = new AppInfo();
    appInfo3 = new AppInfo();
    appInfo5 = new AppInfo();
    appInfo.label = "AAAA";
    appInfo2.label = "AAAB";
    appInfo3.label = "ABBA";
    appInfo4.label = "ABBA";
    appInfo5.label = "ABA";
  }


  @Test
  public void compareToTest() {

    int test1 = appInfo.compareTo(appInfo2);
    int test2 = appInfo2.compareTo(appInfo3);
    int test3 = appInfo3.compareTo(appInfo4);
    int test4 = appInfo4.compareTo(appInfo);

    assertEquals( -1, test1);
    assertEquals( -1, test2);
    assertEquals( 1, test3);
    assertEquals( 1, test4);

  }
}