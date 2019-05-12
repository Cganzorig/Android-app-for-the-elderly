package com.launcher.ava.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTableTest {

  private ContactInfoTable contactInfoTable;
  private ContactTableRow contactTableRow;
  private ContactTableRow contactTableRow2;

  @Before
  public void init(){
    contactInfoTable = new ContactInfoTable();
    contactTableRow = new ContactTableRow("01", "Jojo", "012345678", "AK");
    contactTableRow2 = new ContactTableRow("02", "Jaja", "987654321", "BK");
  }

  @Test
  public void addTest() {
    contactInfoTable.add(contactTableRow);
    assertNotNull(contactInfoTable.getColumnWithMime("MimeType", "AK"));
  }

  @Test
  public void isEmptyTest() {
    assertTrue(contactInfoTable.isEmpty());
    contactInfoTable.add(contactTableRow);
    assertFalse(contactInfoTable.isEmpty());
  }

  @Test
  public void getColumnWithMime() {
    contactInfoTable.add(contactTableRow);
    contactInfoTable.add(contactTableRow2);
    assertEquals("01", contactInfoTable.getColumnWithMime("_id", "AK"));
    assertEquals("02", contactInfoTable.getColumnWithMime("_id", "BK"));
  }
}