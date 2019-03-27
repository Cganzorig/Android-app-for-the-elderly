package com.launcher.ava.elderlylauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.launcher.ava.utilities.ContactInfoTable;
import com.launcher.ava.utilities.ContactTableRow;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  public class ContactInfo {
    String displayName;
    String number;
    String hasWhatsapp;
    String whatsappVoiceId;
  }

  private ContactInfoTable table = new ContactInfoTable();

  private int selectedButton;

  TextView quickCallButton1;
  TextView addButton1;

  TextView quickCallButton2;
  TextView addButton2;

  ConstraintLayout addAndSearch;
  Guideline guide0;




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    quickCallButton1 = findViewById(R.id.textFirstFav);
    addButton1 = findViewById(R.id.textChangeFirstFav);

    quickCallButton2 = findViewById(R.id.textSecondFav);
    addButton2 = findViewById(R.id.textChangeSecondFav);

    addAndSearch = findViewById(R.id.cLayoutPickContact);
    guide0 = findViewById(R.id.firstPhoneScreenguide0);
    LayoutParams params = (LayoutParams) addAndSearch.getLayoutParams();
    //params.topToTop = R.id.firstPhoneScreenguide0;

    displayFavouriteContacts();
  }


  public void putContactInfoInSharedPrefs(String sharedPrefName, ContactInfo tmpInfo) {

    SharedPreferences sp = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("displayName", tmpInfo.displayName);
    editor.putString("number", tmpInfo.number);
    editor.putString("whatsappVoiceId", tmpInfo.whatsappVoiceId);
    editor.apply();
  }

  public void passExtraInfoToNewIntent(String sharedPrefName) {

    Intent intent = new Intent(this, SecondPhoneScreen.class);
    SharedPreferences sp = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

    String displayName = sp.getString("displayName", "");
    String number = sp.getString("number", "");
    String whatsappVoiceId = sp.getString("whatsappVoiceId", "");

    intent.putExtra("number", number);
    intent.putExtra("displayName", displayName);
    intent.putExtra("whatsappVoiceId", whatsappVoiceId);

    startActivity(intent);

  }

  public void pickFromList() {
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pressQuickCallButton1(View view) {
    // if equals default value, pressing is the same as overwriting
    if(quickCallButton1.getText().toString()
        .equals(getResources().getString(R.string.add_fav_contact))) {
      this.selectedButton = 1;
      pickFromList();
    } else {
      passExtraInfoToNewIntent("button1");
    }
  }

  public void pressEllipsis1(View view) {
    this.selectedButton = 1;
    pickFromList();
  }

  public void pressQuickCallButton2(View view) {
    // if equals default value, pressing is the same as overwriting
    if(quickCallButton2.getText().toString()
        .equals(getResources().getString(R.string.add_fav_contact))) {
      this.selectedButton = 2;
      pickFromList();
    } else {
      passExtraInfoToNewIntent("button2");
    }
  }

  public void pressEllipsis2(View view) {
    this.selectedButton = 2;
    pickFromList();
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
      getInfo(contactUri);

      // temp housing for data of selected contact
      ContactInfo tmpInfo = new ContactInfo();

      String mimeWhatsappVoice = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
      String mimePhoneVoice = "vnd.android.cursor.item/phone_v2";

      tmpInfo.displayName = this.table.getColumnWithMime("displayName", mimePhoneVoice);
      tmpInfo.number = this.table.getColumnWithMime("phoneNumber", mimePhoneVoice);
      tmpInfo.whatsappVoiceId = this.table.getColumnWithMime("_id", mimeWhatsappVoice);

      switch (this.selectedButton) {
        case 1:
          putContactInfoInSharedPrefs("button1", tmpInfo);
          break;
        case 2:
          putContactInfoInSharedPrefs("button2", tmpInfo);
          break;
        case 5:
          Intent intent = new Intent(this, SecondPhoneScreen.class);
          intent.putExtra("number", tmpInfo.number);
          intent.putExtra("whatsapp", tmpInfo.hasWhatsapp);
          intent.putExtra("contactId", tmpInfo.whatsappVoiceId);
          startActivity(intent);
          break;
      }
      displayFavouriteContacts();
    }
  }

  public void displayFavouriteContacts() {

    SharedPreferences sp1 = getSharedPreferences("button1", Context.MODE_PRIVATE);

    if(sp1.contains("displayName")){
      String s = "Call "+ sp1.getString("displayName", "");
      quickCallButton1.setText(s);
    } else {
      quickCallButton1.setText("ERROR");
    }

    SharedPreferences sp2 = getSharedPreferences("button2", Context.MODE_PRIVATE);

    if(sp2.contains("displayName")) {
      String s = "Call " + sp2.getString("displayName", "");
      quickCallButton2.setText(s);
    }
    else {
      quickCallButton1.setText("ERROR");
    }

  }

  private String queryCursor(Uri contactUri) {

    String answer = "False";
    String[] projection = new String[] {Phone.NUMBER};

    Cursor cursor = getContentResolver()
        .query(contactUri, projection, null, null, null);

    if (cursor != null && cursor.moveToFirst()) {
      answer = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
    }
    cursor.close();
    return answer;
  }

  private void getInfo(Uri contactUri) {
    ContactInfoTable table = new ContactInfoTable();

    String target = queryCursor(contactUri);

    Cursor cursor = getContentResolver().query(
        ContactsContract.Data.CONTENT_URI,
        null, null, null,
        ContactsContract.Contacts.DISPLAY_NAME);

    while (cursor.moveToNext()) {
      String phoneNumber= cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
      if(phoneNumber != null && phoneNumber.contains(target.substring(1))) {
        String _id = cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID));
        String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
        String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
        table.add(new ContactTableRow(_id, displayName, phoneNumber, mimeType));
      }
    }
    cursor.close();
    this.table = table;
  }

}