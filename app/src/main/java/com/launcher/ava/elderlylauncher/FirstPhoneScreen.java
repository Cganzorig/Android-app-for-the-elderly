package com.launcher.ava.elderlylauncher;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launcher.ava.helperApp.AppScreen;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  // pass these to second screen
  private String number;
  private String hasWhatsapp;
  private String contactId;

  private int selectedButton;

  TextView contactNumber1 = null;
  TextView contactNumber2 = null;
  TextView contactNumber3 = null;
  TextView contactNumber4 = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    contactNumber1 = (TextView) findViewById(R.id.textView1);
    contactNumber2 = (TextView) findViewById(R.id.textView2);
    contactNumber3 = (TextView) findViewById(R.id.textView3);
    contactNumber4 = (TextView) findViewById(R.id.textView4);

    Button buttonPickContact1 = (Button)findViewById(R.id.pickButton1);
    buttonPickContact1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectedButton = 1;
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
      }
    });

    Button buttonPickContact2 = (Button)findViewById(R.id.pickButton2);
    buttonPickContact2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectedButton = 2;
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
      }
    });

    Button buttonPickContact3 = (Button)findViewById(R.id.pickButton3);
    buttonPickContact3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectedButton = 3;
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
      }
    });

    Button buttonPickContact4 = (Button)findViewById(R.id.pickButton4);
    buttonPickContact4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectedButton = 4;
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
      }
    });

  }

  // pick contact form list
  public void pickContactFromList(View view) {
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  // get contact data (boilerplate)
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case (PICK_CONTACT_REQUEST):
        if (resultCode == Activity.RESULT_OK) {

          Uri contactData = data.getData();

          Cursor c = getContentResolver().query(contactData, null, null, null, null);

          if (c.moveToFirst()) {
            String name1 = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            //String contactId1 = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            //String hasNumber1 = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            switch (selectedButton) {
              case 1:
                contactNumber1.setText(name1);
                break;
              case 2:
                contactNumber2.setText(name1);
                break;
              case 3:
                contactNumber3.setText(name1);
                break;
              case 4:
                contactNumber4.setText(name1);
                break;
            }
          }
          break;
        }
    }


//    if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
//      // Get the URI and query the content provider for the phone number
//      Uri contactUri = data.getData();
//
//      // get number
//      String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
//      Cursor cursor = getContentResolver()
//          .query(contactUri, projection, null, null, null);
//
//      // If the cursor returned is valid, get the phone number and check for Whatsapp
//      if (cursor != null && cursor.moveToFirst()) {
//        int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//        this.number = cursor.getString(numberIndex);
//        this.contactId = convertNumberToID(number);
//        this.hasWhatsapp = (hasWhatsApp(this.contactId)) ? "True" : "False";
//      }
//      cursor.close();
//    }
//
//    // start second scree; pass parameters
//    Intent intent = new Intent(this, SecondPhoneScreen.class);
//    intent.putExtra("number", this.number);
//    intent.putExtra("whatsapp", this.hasWhatsapp);
//    intent.putExtra("contactId", this.contactId);
//    startActivity(intent);
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