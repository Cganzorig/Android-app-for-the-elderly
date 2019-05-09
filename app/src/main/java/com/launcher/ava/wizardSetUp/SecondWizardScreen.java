package com.launcher.ava.wizardSetUp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.FirstPhoneScreen;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.utilities.ContactInfoTable;
import com.launcher.ava.utilities.ContactTableRow;

public class SecondWizardScreen extends AppCompatActivity {

  static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;  // The request code

  private ContactInfoTable table = new ContactInfoTable();

  ConstraintLayout addAndRemove;
  ConstraintLayout whiteBlock;
  Button nextBtn;
  int buttoncount;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    this.addAndRemove = findViewById(R.id.cLayoutWizardBtn);
    this.whiteBlock = findViewById(R.id.cLayoutWizardWhiteBlock);
    this.nextBtn= findViewById(R.id.nextBtn);
    this.buttoncount = 0;

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_wizard_screen);
    displayFavouriteContacts();
  }

  @Override
  protected void onResume() {
    super.onResume();
    displayFavouriteContacts();
  }

  public void pressFav(View view) {
    this.buttoncount += 1;
    pickFromList();
  }

  public void pickFromList() {
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (ContextCompat.checkSelfPermission(this,
      Manifest.permission.READ_CONTACTS)
      != PackageManager.PERMISSION_GRANTED) {

      // No explanation needed; request the permission
      ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.READ_CONTACTS},
        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

      // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
      // app-defined int constant. The callback method gets the
      // result of the request.

    } else {
      handleResult(requestCode, resultCode, data);
    }
  }

  private void handleResult(int requestCode, int resultCode, Intent data) {

    if (this.buttoncount > 3) {
      this.buttoncount -= 3;
    }

    if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS && resultCode == RESULT_OK) {
      // data is returned from startActivityFromResult()
      Uri contactUri = data.getData();
      getInfo(contactUri);

      // temp housing for data of selected contact
      ContactInfo tmpInfo = new ContactInfo();

      // mime types we need
      String mimeWhatsappVoice = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
      String mimePhoneVoice = "vnd.android.cursor.item/phone_v2";
      String mimeViberVoice = "vnd.android.cursor.item/vnd.com.viber.voip.viber_number_call";
      // String mimeMessengerVoice = "";
      String mimeSkypeVoice = "vnd.android.cursor.item/com.skype4life.phone";

      tmpInfo.displayName = this.table.getColumnWithMime("displayName", mimePhoneVoice);
      tmpInfo.number = this.table.getColumnWithMime("phoneNumber", mimePhoneVoice);
      tmpInfo.whatsappVoiceId = this.table.getColumnWithMime("_id", mimeWhatsappVoice);
      tmpInfo.viberVoiceId = this.table.getColumnWithMime("_id", mimeViberVoice);
      tmpInfo.skypeVoiceId = this.table.getColumnWithMime("_id", mimeSkypeVoice);

      switch ((this.buttoncount)) {
        case 1:
          putContactInfoInSharedPrefs("button1", tmpInfo);
          displayFavouriteContacts();
          break;
        case 2:
          putContactInfoInSharedPrefs("button2", tmpInfo);
          displayFavouriteContacts();
          break;
        case 3:
          putContactInfoInSharedPrefs("button3", tmpInfo);
          displayFavouriteContacts();
          break;
      }
      displayFavouriteContacts();
    }
  }



  public void putContactInfoInSharedPrefs(String sharedPrefName, ContactInfo tmpInfo) {

    SharedPreferences sp = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString("displayName", tmpInfo.displayName);
    editor.putString("number", tmpInfo.number);
    editor.putString("whatsappVoiceId", tmpInfo.whatsappVoiceId);
    editor.putString("viberVoiceId", tmpInfo.viberVoiceId);
    editor.putString("skypeVoiceId", tmpInfo.skypeVoiceId);
    editor.apply();
  }


  private void getInfo(Uri contactUri) {
    ContactInfoTable table = new ContactInfoTable();

    String target = queryCursor(contactUri);

    Cursor cursor = getContentResolver().query(
      ContactsContract.Data.CONTENT_URI,
      null, null, null,
      ContactsContract.Contacts.DISPLAY_NAME);

    assert cursor != null;
    while (cursor.moveToNext()) {
      String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
      if (phoneNumber != null) {
        phoneNumber = phoneNumber.replaceAll("\\s+","");
        target = target.replaceAll("\\s+","");
      }


      if (phoneNumber != null && phoneNumber.contains(target.substring(2))) {
        String _id = cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID));
        String displayName = cursor
          .getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
        String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
        table.add(new ContactTableRow(_id, displayName, phoneNumber, mimeType));
        //Log.d("t",_id + " " + displayName +" "+ phoneNumber + " "+ mimeType);

      }
    }
    cursor.close();
    this.table = table;
  }

  private String queryCursor(Uri contactUri) {

    String answer = "False";
    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};

    Cursor cursor = getContentResolver()
      .query(contactUri, projection, null, null, null);

    if (cursor != null && cursor.moveToFirst()) {
      answer = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    }
    assert cursor != null;
    cursor.close();
    return answer;
  }

  public void displayFavouriteContacts() {
    TextView tv = findViewById(R.id.nextBtn);
    SharedPreferences sp1 = getSharedPreferences("button1", Context.MODE_PRIVATE);
    TextView tv1 = findViewById(R.id.textFirstFavWizard);
    if (sp1.contains("displayName")) {
      String s = sp1.getString("displayName", "");

      tv1.setText(s);
      tv.setText("Next");

    }else {
      tv1.setText(getResources().getString(R.string.add_fav_contact));
    }

    SharedPreferences sp2 = getSharedPreferences("button2", Context.MODE_PRIVATE);
    TextView tv2 = findViewById(R.id.textSecondFavWizard);
    if (sp2.contains("displayName")) {
      String s = sp2.getString("displayName", "");
      tv2.setText(s);
      tv.setText("Next");

    }else {
      tv2.setText(getResources().getString(R.string.add_fav_contact));
    }

    SharedPreferences sp3 = getSharedPreferences("button3", Context.MODE_PRIVATE);
    TextView tv3 = findViewById(R.id.textThirdFavWizard);
    if (sp3.contains("displayName")) {
      String s = sp3.getString("displayName", "");
      tv3.setText(s);
      tv.setText("Next");

    }else {
      tv3.setText(getResources().getString(R.string.add_fav_contact));
    }

  }


  public void doNothing(View view) {
  }

  public void goToNextPage(View view) {
    Intent intent = new Intent(this, FirstPhoneScreen.class);
    startActivity(intent);
  }


}

