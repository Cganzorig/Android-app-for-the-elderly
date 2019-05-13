package com.launcher.ava.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebsiteTest {

  Website website;
  @Before
  public void init(){
    website =  new Website("name", "url");
  }

  @Test
  public void WebsitefirstconstTest(){
    Website website2 = new Website("name1", "url1");
    assertNotEquals(website2, website);
  }

  @Test
  public void WebsiteSecondconstTest(){
    Website website3 = new Website(2,"name1", "url1" );
    assertNotEquals(website3, website);
  }


}