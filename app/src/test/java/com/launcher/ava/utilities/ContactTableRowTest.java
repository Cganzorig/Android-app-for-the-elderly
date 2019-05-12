package com.launcher.ava.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTableRowTest {

  private ContactTableRow contactTableRow;

  @Before
  public void init(){
    contactTableRow = new ContactTableRow("AAA", "BBB",
      "CCC", "DDD");
  }

  @Test
  public void getColumnTest() {

    assertEquals("AAA", contactTableRow.getColumn("_id"));

    assertEquals("BBB", contactTableRow.getColumn("displayName"));

    assertEquals("CCC", contactTableRow.getColumn("phoneNumber"));

    assertEquals("DDD", contactTableRow.getColumn("mimeType"));
  }
}