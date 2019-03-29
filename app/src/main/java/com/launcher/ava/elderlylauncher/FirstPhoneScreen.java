package com.launcher.ava.elderlylauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.utilities.ContactInfoTable;
import com.launcher.ava.utilities.ContactTableRow;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  private ContactInfoTable table = new ContactInfoTable();
  private int numFavs;

  private int selectedButton;

  ConstraintLayout addAndSearch;
  ConstraintLayout whiteBlock;
  Button add_btn;
  Button remove_btn;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    this.addAndSearch = findViewById(R.id.cLayoutBtn);
    this.whiteBlock = findViewById(R.id.cLayoutWhiteBlock);
    this.add_btn = findViewById(R.id.add_button);
    this.remove_btn = findViewById(R.id.remove_button);

    displayFavouriteContacts();
  }


  public void setWhiteBlocks() {
    LayoutParams params1 = (LayoutParams) this.addAndSearch.getLayoutParams();
    LayoutParams params2 = (LayoutParams) this.whiteBlock.getLayoutParams();

    switch (this.numFavs) {
      case 0:
        params1.topToTop= R.id.firstPhoneScreenguide1;
        params1.bottomToTop= R.id.firstPhoneScreenguide2;
        params2.topToTop= R.id.firstPhoneScreenguide2;
        params2.bottomToTop= R.id.firstPhoneScreenguide6;
        break;
      case 1:
        params1.topToTop= R.id.firstPhoneScreenguide2;
        params1.bottomToTop= R.id.firstPhoneScreenguide3;
        params2.topToTop= R.id.firstPhoneScreenguide3;
        params2.bottomToTop= R.id.firstPhoneScreenguide6;
        break;
      case 2:
        params1.topToTop= R.id.firstPhoneScreenguide3;
        params1.bottomToTop= R.id.firstPhoneScreenguide4;
        params2.topToTop= R.id.firstPhoneScreenguide4;
        params2.bottomToTop= R.id.firstPhoneScreenguide6;
        break;
      case 3:
        params1.topToTop= R.id.firstPhoneScreenguide4;
        params1.bottomToTop= R.id.firstPhoneScreenguide5;
        params2.topToTop= R.id.firstPhoneScreenguide5;
        params2.bottomToTop= R.id.firstPhoneScreenguide6;
        break;

    }
    addAndSearch.setLayoutParams(params1);
    whiteBlock.setLayoutParams(params2);
  }

  public void doNothing(View view) {}

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

  public void pressMinus(View view) {
    displayFavouriteContacts();
    switch (this.numFavs) {
      case 3:
        pressEllipsis(findViewById(R.id.textChangeThirdFav));
        break;
      case 2:
        pressEllipsis(findViewById(R.id.textChangeSecondFav));
        break;
      case 1:
        pressEllipsis(findViewById(R.id.textChangeFirstFav));
        break;
        default:
          break;
    }
    displayFavouriteContacts();
  }

  public void pressPlus(View view) {
    displayFavouriteContacts();
    switch (this.numFavs) {
      case 0:
        this.selectedButton = 1;
        this.numFavs +=1;
        pickFromList();
        break;
      case 1:
        this.selectedButton = 2;
        this.numFavs +=1;
        pickFromList();
        break;
      case 2:
        this.selectedButton = 3;
        this.numFavs +=1;
        pickFromList();
        break;
        default:
          break;
    }
    displayFavouriteContacts();
  }

  public void passExtraInfoToNewIntent(String sharedPrefName) {

    Intent intent = new Intent(this, SecondPhoneScreen.class);
    SharedPreferences sp = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);

    String displayName = sp.getString("displayName", "");
    String number = sp.getString("number", "");
    String whatsappVoiceId = sp.getString("whatsappVoiceId", "");
    String viberVoiceId = sp.getString("viberVoiceId", "");


    intent.putExtra("number", number);
    intent.putExtra("displayName", displayName);
    intent.putExtra("whatsappVoiceId", whatsappVoiceId);
    intent.putExtra("viberVoiceId", viberVoiceId);


    startActivity(intent);

  }

  public void pickFromList() {
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
    pickContactIntent.setType(Phone.CONTENT_TYPE);
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  public void pressFav(View view) {
    TextView tv;
    String spName;
    switch (view.getId()){
      case R.id.textFirstFav:
        tv = findViewById(R.id.textFirstFav);
        spName = "button1";
        this.selectedButton = 1;
        break;
      case R.id.textSecondFav:
        tv = findViewById(R.id.textSecondFav);
        spName = "button2";
        this.selectedButton = 2;
        break;
      case R.id.textThirdFav:
        tv = findViewById(R.id.textThirdFav);
        spName = "button3";
        this.selectedButton = 3;
        break;
        default:
          tv = findViewById(R.id.textPickContact);
          spName = "button4";
          this.selectedButton = 4;
    }
    if(tv.getText().toString().equals(getResources().getString(R.string.add_fav_contact))) {
      pickFromList();
    } else if (tv.getText().toString().equals(getResources().getString(R.string.pick_contact_from_list))) {
      pickFromList();
    } else {
      passExtraInfoToNewIntent(spName);
    }
  }

  public void pressEllipsis(View view) {
    TextView tv;
    String spName;
    switch (view.getId()){
      case R.id.textChangeFirstFav:
        tv = findViewById(R.id.textFirstFav);
        spName = "button1";
        break;
      case R.id.textChangeSecondFav:
        tv = findViewById(R.id.textSecondFav);
        spName = "button2";
        break;
      case R.id.textChangeThirdFav:
        tv = findViewById(R.id.textThirdFav);
        spName = "button3";
        break;
      default:
        tv = findViewById(R.id.textFirstFav);
        spName = "button1";
    }
    SharedPreferences sp1 = getSharedPreferences(spName, MODE_PRIVATE);
    SharedPreferences.Editor editor = sp1.edit();
    editor.clear();
    editor.apply();
    tv.setText(R.string.add_fav_contact);
    displayFavouriteContacts();
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

      // mime types we need
      String mimeWhatsappVoice = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
      String mimePhoneVoice = "vnd.android.cursor.item/phone_v2";
      String mimeViberVoice = "vnd.android.cursor.item/vnd.com.viber.voip.viber_number_call";
      String mimeMessengerVoice = "";
      String mimeSkypeVoice = "vnd.android.cursor.item/com.skype4life.phone";


      tmpInfo.displayName = this.table.getColumnWithMime("displayName", mimePhoneVoice);
      tmpInfo.number = this.table.getColumnWithMime("phoneNumber", mimePhoneVoice);
      tmpInfo.whatsappVoiceId = this.table.getColumnWithMime("_id", mimeWhatsappVoice);
      tmpInfo.viberVoiceId = this.table.getColumnWithMime("_id", mimeViberVoice);
      tmpInfo.skypeVoiceId = this.table.getColumnWithMime("_id", mimeSkypeVoice);

      switch (this.selectedButton) {
        case 1:
          putContactInfoInSharedPrefs("button1", tmpInfo);
          break;
        case 2:
          putContactInfoInSharedPrefs("button2", tmpInfo);
          break;
        case 3:
          putContactInfoInSharedPrefs("button3", tmpInfo);
          break;
        case 4:
          putContactInfoInSharedPrefs("button4", tmpInfo);
          passExtraInfoToNewIntent("button4");
          break;
      }
      displayFavouriteContacts();
    }
  }

  public void displayFavouriteContacts() {

    int numFavs = 0;
    SharedPreferences sp1 = getSharedPreferences("button1", Context.MODE_PRIVATE);
    if(sp1.contains("displayName")) {
      String s = "Call " + sp1.getString("displayName", "");
      TextView tv1 = findViewById(R.id.textFirstFav);
      tv1.setText(s);
      numFavs += 1;
    }

    SharedPreferences sp2 = getSharedPreferences("button2", Context.MODE_PRIVATE);
    if(sp2.contains("displayName")) {
      String s = "Call " + sp2.getString("displayName", "");
      TextView tv2 = findViewById(R.id.textSecondFav);
      tv2.setText(s);
      numFavs +=1;
    }

    SharedPreferences sp3 = getSharedPreferences("button3", Context.MODE_PRIVATE);
    if(sp3.contains("displayName")) {
      String s = "Call " + sp3.getString("displayName", "");
      TextView tv3 = findViewById(R.id.textThirdFav);
      tv3.setText(s);
      numFavs +=1;
    }

    this.numFavs= numFavs;
    setWhiteBlocks();
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
      if(phoneNumber != null) {
        phoneNumber = phoneNumber.replace(" ", "");
        target = target.replace(" ", "");
      }
      if(phoneNumber != null && phoneNumber.contains(target.substring(2))) {
        String _id = cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID));
        String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
        String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
        table.add(new ContactTableRow(_id, displayName, phoneNumber, mimeType));
        //Log.d("t",_id + " " + displayName +" "+ phoneNumber + " "+ mimeType);

      }
    }
    cursor.close();
    this.table = table;
  }

}