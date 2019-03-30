package com.launcher.ava.utilities;

public class ContactTableRow {

  private String _id;
  private String displayName;
  private String phoneNumber;
  private String mimeType;

  public ContactTableRow(
    String _id,
    String displayName,
    String phoneNumber,
    String mimeType) {

    this._id = _id;
    this.displayName = displayName;
    this.phoneNumber = phoneNumber;
    this.mimeType = mimeType;
  }

  public String getColumn(String what) {

    if (what.equals("displayName")) {
      return displayName;
    } else if (what.equals("phoneNumber")) {
      return phoneNumber;
    } else if (what.equals("mimeType")) {
      return mimeType;
    } else if (what.equals("_id")) {
      return _id;
    } else {
      return "NONE";
    }
  }
}
