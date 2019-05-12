package com.launcher.ava.utilities;

import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.launcher.ava.utilities.WebsiteDatabase.getUrl;
import static org.junit.Assert.*;

public class WebsiteDatabaseTest {


  private ArrayList websiteList;
  private List websiteDatabase;
  private List websiteDatabase2;

  @Before
  public void init() {
    websiteDatabase = WebsiteDatabase.getInstance();
    websiteDatabase2 = WebsiteDatabase.getInstance();

    websiteList = new ArrayList<>();
    websiteList.add(new Website(R.drawable.ic_amazon, "Amazon", "https://www.amazon.co.uk/"));


  }


  @Test
  public void getInstanceTest() {
   assertEquals(websiteDatabase, websiteDatabase2);
  }

  @Test
  public void getUrlTest() {
    assertEquals("https://www.amazon.co.uk/", getUrl("Amazon"));
  }
}