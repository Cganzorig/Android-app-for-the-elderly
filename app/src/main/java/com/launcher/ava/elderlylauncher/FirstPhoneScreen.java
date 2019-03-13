package com.launcher.ava.elderlylauncher;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  private class ContactInfo {
    String displayName;
    String id;
    String number;
    String hasWhatsapp;
    String hasViber;
  }

  private ContactInfo contactInfo4;
  private ContactInfo contactInfo5;

  private static final int ID_TYPE = 1;
  private static final int NUMBER_TYPE = 2;
  private static final int NAME_TYPE = 3;
  private static final int MIME_TYPE = 4;

  private int selectedButton;

  TextView quickCallButton4 = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    quickCallButton4 = findViewById(R.id.textView4);
  }

  public void pressPlusButton4(View view) {
    this.selectedButton = 4;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pressQuickCallButton4(View view) {
    Intent intent = new Intent(this, SecondPhoneScreen.class);
    intent.putExtra("number", contactInfo4.number);
    intent.putExtra("whatsapp", contactInfo4.hasWhatsapp);
    intent.putExtra("contactId", contactInfo4.id);
    startActivity(intent);
  }

  public void pickContactFromList(View view) {
    this.selectedButton = 5;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {

      // data is returned from startActivityFromResult()
      Uri contactUri = data.getData();

      // temp housing for data of selected contact
      ContactInfo tmpInfo = new ContactInfo();
      tmpInfo.id = queryCursor(contactUri, ID_TYPE);
      tmpInfo.displayName = queryCursor(contactUri, NAME_TYPE);
      tmpInfo.number = queryCursor(contactUri, NUMBER_TYPE);
      tmpInfo.hasWhatsapp = (hasWhatsApp(tmpInfo.id)) ? "True" : "False";

      Intent intent = new Intent(this, SecondPhoneScreen.class);
      switch (this.selectedButton) {
        case 4:
          this.contactInfo4 = tmpInfo;
          quickCallButton4.setText(contactInfo4.displayName);
          break;
        case 5:
          intent.putExtra("number", tmpInfo.number);
          intent.putExtra("whatsapp", tmpInfo.hasWhatsapp);
          intent.putExtra("contactId", tmpInfo.id);
          startActivity(intent);
          break;
      }
    }
  }

  private boolean hasWhatsApp(String contact_id) {
    String[] projection = new String[] { RawContacts._ID };
    String selection = Data.CONTACT_ID + " = ? AND account_type IN (?)";
    String[] selectionArgs = new String[] { contact_id, "com.whatsapp" };
    Cursor cursor = getContentResolver()
        .query(RawContacts.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null);
    if(cursor.moveToNext()){
      cursor.close();
      return TRUE;
    }
    return FALSE;
  }

  private String queryCursor(Uri contactUri, int infoType) {

    String content;
    String answer = "False";
    switch (infoType) {
      case ID_TYPE:
        content = Phone.CONTACT_ID;
        break;
      case NUMBER_TYPE:
        content = Phone.NUMBER;
        break;
      case NAME_TYPE:
        content = Phone.DISPLAY_NAME;
        break;
      case MIME_TYPE:
        default:
          content = Phone._ID;
    }
    String[] projection = new String[] {content};

    Cursor cursor = getContentResolver()
        .query(contactUri, projection, null, null, null);

    if (cursor != null && cursor.moveToFirst()) {
      answer = cursor.getString(cursor.getColumnIndex(content));
    }
    cursor.close();
    return answer;
  }
}