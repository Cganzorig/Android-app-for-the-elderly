package com.launcher.ava.elderlylauncher;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  // pass these to second screen
  private String number;
  private String hasWhatsapp;
  private String contactId;

  private int selectedButton;

  TextView contactNumber4 = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    contactNumber4 = findViewById(R.id.textView4);
  }

  public void pressButton4(View view) {
    this.selectedButton = 4;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pickContactFromList(View view) {
    this.selectedButton = 5;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  // get contact data (boilerplate)
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
      // Get the URI and query the content provider for the phone number
      Uri contactUri = data.getData();

      // get number
      String[] projection = new String[]{Phone.NUMBER};
      Cursor cursor = getContentResolver()
          .query(contactUri, projection, null, null, null);

      // If the cursor returned is valid, get the phone number and check for Whatsapp
      if (cursor != null && cursor.moveToFirst()) {
        if(this.selectedButton == 4) {
          String name1 = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
          contactNumber4.setText(name1);
        }
        if(this.selectedButton == 5) {
        int numberIndex = cursor.getColumnIndex(Phone.NUMBER);
        this.number = cursor.getString(numberIndex);
        this.contactId = convertNumberToID(number);
        this.hasWhatsapp = (hasWhatsApp(this.contactId)) ? "True" : "False";
        }
      }
      cursor.close();
    }

    if(this.selectedButton ==5) {
    // start second scree; pass parameters
    Intent intent = new Intent(this, SecondPhoneScreen.class);
    intent.putExtra("number", this.number);
    intent.putExtra("whatsapp", this.hasWhatsapp);
    intent.putExtra("contactId", this.contactId);
    startActivity(intent);}
  }

  private boolean hasWhatsApp(String contact_id) {
    String[] projection = new String[] { ContactsContract.RawContacts._ID };
    String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
    String[] selectionArgs = new String[] { contact_id, "com.whatsapp" };
    Cursor cursor = this.getContentResolver()
        .query(ContactsContract.RawContacts.CONTENT_URI,
            projection, selection, selectionArgs, null);
    return cursor.moveToNext();
  }

  private String convertNumberToID(String number) {
    ContentResolver contentResolver = FirstPhoneScreen.this.getContentResolver();

    Uri uri = Uri
        .withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

    String[] projection = new String[]
        {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

    Cursor cursor =
        contentResolver.query(
            uri,
            projection,
            null,
            null,
            null);

    cursor.moveToNext();
    String contactId = cursor
        .getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
    cursor.close();
    return contactId;
  }
}