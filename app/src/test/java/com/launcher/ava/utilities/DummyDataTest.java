package com.launcher.ava.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DummyDataTest {

  private ArrayList<String> stores;
  private ArrayList<String> stores2;
  private DummyData dummyData;

  @Before
  public void init(){
    stores =  new ArrayList<String>();
    stores.add("Amazon");
    stores.add("Sears");
    stores.add("Ebay Home");
    stores.add("Macys Home");
    stores.add("JCpenney Kids");
    stores.add("Ebay Electronics");
    stores.add("Amazon Appliance");
    stores.add("Ebay Mobiles");
    stores.add("Ebay Kids");
    stores.add("Amazon Fashion");
    stores.add("Ebay Travel");
    stores.add("JCpenney Home");
    stores.add("JCpenney Luggage");
    stores.add("JCpenney Appliance");
    stores.add("JCpenney Fashion");
    stores.add("Amazon Luggage");
    stores.add("Macys Jewellery");
    stores.add("JCpenney Jewellery");
    stores.add("Amazon Jewellery");


    stores2 =  new ArrayList<String>();
    stores2.add("Ebay Home");
    stores2.add("Ebay Electronics");
    stores2.add("Ebay Mobiles");
    stores2.add("Ebay Kids");
    stores2.add("Ebay Travel");

    dummyData = new DummyData();
  }


  @Test
  public void getStoresTest() {
    assertEquals(stores, dummyData.getStores());
  }

  @Test
  public void filterDataTest() {
    assertEquals(stores2, dummyData.filterData("Ebay"));

  }
}