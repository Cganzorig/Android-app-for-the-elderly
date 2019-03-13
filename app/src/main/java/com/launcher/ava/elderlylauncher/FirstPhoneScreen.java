package com.launcher.ava.elderlylauncher;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

  TextView quickCallButton1 = null;
  TextView quickCallButton2 = null;
  TextView quickCallButton3 = null;
  TextView quickCallButton4 = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    quickCallButton1 = findViewById(R.id.textView1);
    quickCallButton2 = findViewById(R.id.textView2);
    quickCallButton3 = findViewById(R.id.textView3);
    quickCallButton4 = findViewById(R.id.textView4);

    DisplayFavouriteContacts();
  }

  public void pressPlusButton1(View view) {
    this.selectedButton = 1;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pressPlusButton2(View view) {
    this.selectedButton = 2;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pressPlusButton3(View view) {
    this.selectedButton = 3;
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
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
        case 1:
          this.contactInfo4 = tmpInfo;
          quickCallButton1.setText(contactInfo4.displayName);

          SharedPreferences sharedPreferences1 = getSharedPreferences("button1", MODE_PRIVATE);
          SharedPreferences.Editor editor1 = sharedPreferences1.edit();
          editor1.putString("phoneNumber1", quickCallButton1.getText().toString());
          editor1.apply();

          break;
        case 2:
          this.contactInfo4 = tmpInfo;
          quickCallButton2.setText(contactInfo4.displayName);

          SharedPreferences sharedPreferences2 = getSharedPreferences("button2", MODE_PRIVATE);
          SharedPreferences.Editor editor2 = sharedPreferences2.edit();
          editor2.putString("phoneNumber2", quickCallButton2.getText().toString());
          editor2.apply();
          break;
        case 3:
          this.contactInfo4 = tmpInfo;
          quickCallButton3.setText(contactInfo4.displayName);

          SharedPreferences sharedPreferences3 = getSharedPreferences("button3", MODE_PRIVATE);
          SharedPreferences.Editor editor3 = sharedPreferences3.edit();
          editor3.putString("phoneNumber3", quickCallButton3.getText().toString());
          editor3.apply();
          break;
        case 4:
          this.contactInfo4 = tmpInfo;
          quickCallButton4.setText(contactInfo4.displayName);

          SharedPreferences sharedPreferences4 = getSharedPreferences("button4", MODE_PRIVATE);
          SharedPreferences.Editor editor4 = sharedPreferences4.edit();
          editor4.putString("phoneNumber4", quickCallButton4.getText().toString());
          editor4.apply();
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

  public void DisplayFavouriteContacts() {
    SharedPreferences sharedPreferences1 = getSharedPreferences("button1", Context.MODE_PRIVATE);
    quickCallButton1.setText(sharedPreferences1.getString("phoneNumber1", ""));

    SharedPreferences sharedPreferences2 = getSharedPreferences("button2", Context.MODE_PRIVATE);
    quickCallButton2.setText(sharedPreferences2.getString("phoneNumber2", ""));

    SharedPreferences sharedPreferences3 = getSharedPreferences("button3", Context.MODE_PRIVATE);
    quickCallButton3.setText(sharedPreferences3.getString("phoneNumber3", ""));

    SharedPreferences sharedPreferences4 = getSharedPreferences("button4", Context.MODE_PRIVATE);
    quickCallButton4.setText(sharedPreferences4.getString("phoneNumber4", ""));
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