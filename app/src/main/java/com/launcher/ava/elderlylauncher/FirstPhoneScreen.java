package com.launcher.ava.elderlylauncher;

import static com.launcher.ava.utilities.GetPhoto.getContactPhoto;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.launcher.ava.utilities.ContactInfo;
import com.launcher.ava.utilities.ContactInfoTable;
import com.launcher.ava.utilities.ContactTableRow;
import java.io.IOException;
import java.io.InputStream;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;  // The request code


  private ContactInfoTable table = new ContactInfoTable();
  private int numFavs;

  private int selectedButton;

  ConstraintLayout addAndRemove;
  ConstraintLayout whiteBlock;
  Button plusBtn;
  Button minusBtn;
  private Intent pickContactIntent;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    this.addAndRemove = findViewById(R.id.cLayoutBtn);
    this.whiteBlock = findViewById(R.id.cLayoutWhiteBlock);
    this.plusBtn = findViewById(R.id.add_button);
    this.minusBtn = findViewById(R.id.remove_button);

    displayFavouriteContacts();
  }

  public void setWhiteBlocks() {
    LayoutParams paramsAddAndRemove = (LayoutParams) this.addAndRemove.getLayoutParams();
    LayoutParams paramsWhiteBlock = (LayoutParams) this.whiteBlock.getLayoutParams();
    TextView tv = findViewById(R.id.textExplainPlusMinus);
    LayoutParams paramsPlus = (LayoutParams) this.plusBtn.getLayoutParams();
    LayoutParams paramsMinus = (LayoutParams) this.minusBtn.getLayoutParams();

    switch (this.numFavs) {
      case 0:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide1;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide2;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide2;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.INVISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_fav);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical5;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical6;
        break;
      case 1:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide2;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide3;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide3;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical1;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical2;
        paramsMinus.startToStart = R.id.firstPhoneScreenVertical3;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical4;
        break;
      case 2:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide3;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide4;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide4;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.VISIBLE);
        tv.setText(R.string.add_remove_fav);

        paramsPlus.startToStart = R.id.firstPhoneScreenVertical1;
        paramsPlus.endToStart = R.id.firstPhoneScreenVertical2;
        paramsMinus.startToStart = R.id.firstPhoneScreenVertical3;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical4;
        break;
      case 3:
        paramsAddAndRemove.topToTop = R.id.firstPhoneScreenguide4;
        paramsAddAndRemove.bottomToTop = R.id.firstPhoneScreenguide5;
        paramsWhiteBlock.topToTop = R.id.firstPhoneScreenguide5;
        paramsWhiteBlock.bottomToTop = R.id.firstPhoneScreenguide6;

        minusBtn.setVisibility(View.VISIBLE);
        plusBtn.setVisibility(View.INVISIBLE);
        tv.setText(R.string.remove_fav);

        paramsMinus.startToStart = R.id.firstPhoneScreenVertical5;
        paramsMinus.endToStart = R.id.firstPhoneScreenVertical6;
        break;

    }
    addAndRemove.setLayoutParams(paramsAddAndRemove);
    whiteBlock.setLayoutParams(paramsWhiteBlock);
    plusBtn.setLayoutParams(paramsPlus);
    minusBtn.setLayoutParams(paramsMinus);
  }

  public void doNothing(View view) {
  }

  public void openDialer(View v) {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:"));
    startActivity(intent);
    finish();
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

  public void pressMinus(View view) {
    TextView tv = null;
    String spName = null;
    displayFavouriteContacts();
    switch (this.numFavs) {
      case 3:
        tv = findViewById(R.id.textThirdFav);
        spName = "button3";
        this.numFavs -= 1;
        break;
      case 2:
        tv = findViewById(R.id.textSecondFav);
        spName = "button2";
        this.numFavs -= 1;
        break;
      case 1:
        tv = findViewById(R.id.textFirstFav);
        spName = "button1";
        this.numFavs -= 1;
        break;
      default:
        break;
    }
    if (tv != null) {
      SharedPreferences sp1 = getSharedPreferences(spName, MODE_PRIVATE);
      SharedPreferences.Editor editor = sp1.edit();
      editor.clear();
      editor.apply();
      tv.setText(R.string.add_fav_contact);
    }
    displayFavouriteContacts();
  }

  public void pressPlus(View view) {
    displayFavouriteContacts();
    switch (this.numFavs) {
      case 0:
        this.selectedButton = 1;
        this.numFavs += 1;
        pickFromList();
        break;
      case 1:
        this.selectedButton = 2;
        this.numFavs += 1;
        pickFromList();
        break;
      case 2:
        this.selectedButton = 3;
        this.numFavs += 1;
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
    startActivityForResult(pickContactIntent, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
  }

  public void pressFav(View view) {
    TextView tv;
    String spName;
    switch (view.getId()) {
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
    if (tv.getText().toString().equals(getResources().getString(R.string.add_fav_contact))) {
      pickFromList();
    } else if (tv.getText().toString()
      .equals(getResources().getString(R.string.pick_contact_from_list))) {
      pickFromList();
    } else {
      passExtraInfoToNewIntent(spName);
    }
  }

  private void handleResult(int requestCode, int resultCode, Intent data) {

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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    this.pickContactIntent = data;

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


  public void displayFavouriteContacts() {

    int numFavs = 0;
    SharedPreferences sp1 = getSharedPreferences("button1", Context.MODE_PRIVATE);
    if (sp1.contains("displayName")) {
      String s = "Call " + sp1.getString("displayName", "");
      TextView tv1 = findViewById(R.id.textFirstFav);
      tv1.setText(s);

      ImageView iv1 = findViewById(R.id.iv1);
      Bitmap photo = getContactPhoto(this, sp1.getString("number", ""));
      if (photo == null) {
        iv1.setImageDrawable(getDrawable(R.drawable.ic_user_));
      } else {
        iv1.setImageBitmap(photo);
      }
      numFavs += 1;
    }

    SharedPreferences sp2 = getSharedPreferences("button2", Context.MODE_PRIVATE);
    if (sp2.contains("displayName")) {
      String s = "Call " + sp2.getString("displayName", "");
      TextView tv2 = findViewById(R.id.textSecondFav);
      tv2.setText(s);
      ImageView iv2 = findViewById(R.id.iv2);
      Bitmap photo = getContactPhoto(this, sp2.getString("number", ""));
      if (photo == null) {
        iv2.setImageDrawable(getDrawable(R.drawable.ic_user_));
      } else {
        iv2.setImageBitmap(photo);
      }
      numFavs += 1;
    }

    SharedPreferences sp3 = getSharedPreferences("button3", Context.MODE_PRIVATE);
    if (sp3.contains("displayName")) {
      String s = "Call " + sp3.getString("displayName", "");
      TextView tv3 = findViewById(R.id.textThirdFav);
      tv3.setText(s);
      ImageView iv3 = findViewById(R.id.iv3);
      Bitmap photo = getContactPhoto(this, sp3.getString("number", ""));
      if (photo == null) {
        iv3.setImageDrawable(getDrawable(R.drawable.ic_user_));
      } else {
        iv3.setImageBitmap(photo);
      }
      numFavs += 1;
    }

    this.numFavs = numFavs;
    setWhiteBlocks();
  }

  private String queryCursor(Uri contactUri) {

    String answer = "False";
    String[] projection = new String[]{Phone.NUMBER};

    Cursor cursor = getContentResolver()
      .query(contactUri, projection, null, null, null);

    if (cursor != null && cursor.moveToFirst()) {
      answer = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
    }
    assert cursor != null;
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

    assert cursor != null;
    while (cursor.moveToNext()) {
      String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
      if (phoneNumber != null) {
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        target = target.replaceAll("\\s+", "");
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

  @Override
  public void onRequestPermissionsResult(int requestCode,
    @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED
          && this.pickContactIntent != null) {
          handleResult(MY_PERMISSIONS_REQUEST_READ_CONTACTS, RESULT_OK, this.pickContactIntent);
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
          Intent backToHome = new Intent(this, FirstPhoneScreen.class);
          startActivity(backToHome);
          finish();
        }
      }

      // other 'case' lines to check for other
      // permissions this app might request.
    }
  }

}