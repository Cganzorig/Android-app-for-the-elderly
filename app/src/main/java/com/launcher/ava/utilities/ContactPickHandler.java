package com.launcher.ava.utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

public class ContactPickHandler {

  public static boolean appInstalledOrNot(Context c, String packName) {
    PackageManager pm = c.getPackageManager();
    try {
      pm.getPackageInfo(packName, 0);
      return true;
    } catch (PackageManager.NameNotFoundException e) {
    }

    return false;
  }

  private static String queryCursor(Context c, Uri contactUri) {

    String answer = "False";
    String[] projection = new String[]{Phone.NUMBER};

    Cursor cursor = c.getContentResolver()
      .query(contactUri, projection, null, null, null);

    if (cursor != null && cursor.moveToFirst()) {
      answer = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
    }
    assert cursor != null;
    cursor.close();
    return answer;
  }

  public static ContactInfoTable getInfo(Context c, Uri contactUri) {
    ContactInfoTable table = new ContactInfoTable();

    String targetPhone = queryCursor(c, contactUri);

    Cursor cursor = c.getContentResolver().query(
      ContactsContract.Data.CONTENT_URI,
      null, null, null,
      ContactsContract.Contacts.DISPLAY_NAME);

    assert cursor != null;
    while (cursor.moveToNext()) {

      String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));

      // we need this to fix the case where errors in transfer of phone books causes
      // names of contacts to incorrectly be set as the phone numbers of contacts
      String rawPhoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));

      if (phoneNumber != null) {
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        phoneNumber = phoneNumber.replaceAll("\\+", "");

        targetPhone = targetPhone.replaceAll("\\s+", "");
        targetPhone = targetPhone.replaceAll("\\+", "");

      }

      if (phoneNumber != null && phoneNumber.contains(targetPhone.substring(2))) {
        String _id = cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID));
        String displayName = cursor
          .getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
        String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));

        if (!rawPhoneNumber.equals(displayName)) {
          table.add(new ContactTableRow(_id, displayName, phoneNumber, mimeType));
        }
        Log.d("t", "LOOKHERE: " +_id + " " + displayName +" "+ phoneNumber + " "+ mimeType);

      }
    }
    cursor.close();
    return table;
  }
}
