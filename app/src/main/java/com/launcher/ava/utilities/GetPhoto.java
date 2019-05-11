package com.launcher.ava.utilities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.IOException;
import java.io.InputStream;

public class GetPhoto {

  public static Bitmap getContactPhoto(Context context, String number) {

    ContentResolver contentResolver = context.getContentResolver();
    String contactId = null;

    Uri uri = Uri
      .withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
    String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME,
      ContactsContract.PhoneLookup._ID};

    Cursor cursor =
      contentResolver.query(
        uri,
        projection,
        null,
        null,
        null);
    if (cursor != null) {
      while (cursor.moveToNext()) {
        contactId = cursor
          .getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
      }
      cursor.close();
    }

    Bitmap photo = null;

    try {
      InputStream inputStream = ContactsContract.Contacts
        .openContactPhotoInputStream(context.getContentResolver(),
          ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));
      if (inputStream != null) {
        photo = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return photo;
  }

}
