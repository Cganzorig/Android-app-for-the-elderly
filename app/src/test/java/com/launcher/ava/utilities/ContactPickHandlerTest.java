package com.launcher.ava.utilities;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.URI;

import static org.junit.Assert.*;

public class ContactPickHandlerTest {

  private ContactInfoTable contactInfoTable;
  private ContactPickHandler contactPickHandler;
  private Cursor cursor;

  @Mock
  private Uri contactUri;
  private Context context;
  private String[] projection;


  @Before
  public void init(){
    contactPickHandler = new ContactPickHandler();
  }


  @Test
  public void getInfoTest() {
    Mockito.when(context.getContentResolver().query(contactUri, projection, null, null, null)).thenReturn(cursor);

  }
}