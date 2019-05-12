package com.launcher.ava.utilities;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class GetPhotoTest {

  Context context;
  Cursor cursor;
  ContentResolver contentResolver;
  Uri uri;

  @Before
  public void init(){
    uri = Mockito.mock(Uri.class);
    context = Mockito.mock(Context.class);
    contentResolver = Mockito.mock(ContentResolver.class);
    cursor = Mockito.mock(Cursor.class);
    String[] position = Mockito.mock(String[].class);
    Mockito.when(context.getContentResolver()).thenReturn(contentResolver);
    Mockito.when(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, anyString())).thenReturn(uri);
    Mockito.when(contentResolver.query(uri,position,null, null, null)).thenReturn(cursor);

  }
  @Test
  public void getContactPhotoTest() {

  }
}