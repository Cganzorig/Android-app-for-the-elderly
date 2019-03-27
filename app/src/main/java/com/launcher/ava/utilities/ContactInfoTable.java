package com.launcher.ava.utilities;

import java.util.ArrayList;
import java.util.Iterator;

public class ContactInfoTable {
  private ArrayList<ContactTableRow> table;

  public ContactInfoTable() {
    table = new ArrayList<>();
  }
  public void add(ContactTableRow row) {table.add(row);}

  public String getColumnWithMime(String column, String mimeType) {
    Iterator<ContactTableRow> iter = table.iterator();
    while(iter.hasNext()) {
      ContactTableRow row = iter.next();
      if(row.getColumn("mimeType").equals(mimeType)) {
        return row.getColumn(column);
      }
    }
    return "NONE";
  }

}
