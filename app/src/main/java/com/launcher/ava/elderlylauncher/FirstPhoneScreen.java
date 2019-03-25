package com.launcher.ava.elderlylauncher;

import static android.app.PendingIntent.getActivity;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Application;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

  public class ContactInfo {
    public String displayName;
    String id;
    String number;
    String hasWhatsapp;
    String hasViber;
  }

  private static final int ID_TYPE = 1;
  private static final int NUMBER_TYPE = 2;
  private static final int NAME_TYPE = 3;
  private static final int MIME_TYPE = 4;

  private int selectedButton;

  Button quickCallButton1;
  Button addButton1;

  Button quickCallButton2;
  Button addButton2;

  Button quickCallButton3;
  Button addButton3;

  Button quickCallButton4;
  Button addButton4;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_phone_screen);

    quickCallButton1 = findViewById(R.id.textView1);
    addButton1 = findViewById(R.id.pickButton1);

    quickCallButton2 = findViewById(R.id.textView2);
    addButton2 = findViewById(R.id.pickButton2);

    quickCallButton3 = findViewById(R.id.textView3);
    addButton3 = findViewById(R.id.pickButton3);

    quickCallButton4 = findViewById(R.id.textView4);
    addButton4 = findViewById(R.id.pickButton4);
    DisplayFavouriteContacts();
  }

  public void pressPlusButton1(View view) {
      this.selectedButton = 1;
      if(addButton1.getText().toString().equals("+")){
          Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
          pickContactIntent.setType(Phone.CONTENT_TYPE);
          startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
      } else {
          quickCallButton1.setText(R.string.default_quick_call);
          addButton1.setText(R.string.plus_sign);
      }
  }

  public void pressPlusButton2(View view) {
    this.selectedButton = 2;
    if(addButton2.getText().toString().equals("+")){
      Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
      pickContactIntent.setType(Phone.CONTENT_TYPE);
      startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    } else {
      quickCallButton2.setText(R.string.default_quick_call);
      addButton2.setText(R.string.plus_sign);
    }
  }

  public void pressPlusButton3(View view) {
    this.selectedButton = 3;
    if(addButton3.getText().toString().equals("+")){
      Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
      pickContactIntent.setType(Phone.CONTENT_TYPE);
      startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    } else {
      quickCallButton3.setText(R.string.default_quick_call);
      addButton3.setText(R.string.plus_sign);
    }
  }

  public void pressPlusButton4(View view) {
    this.selectedButton = 4;
    if(addButton4.getText().toString().equals("+")){
      Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
      pickContactIntent.setType(Phone.CONTENT_TYPE);
      startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    } else {
      quickCallButton4.setText(R.string.default_quick_call);
      addButton4.setText(R.string.plus_sign);
    }
  }
  public void pressQuickCallButton1(View view) {
      if(!quickCallButton1.getText().toString().equals(getResources().getString(R.string.default_quick_call))) {
          Intent intent = new Intent(this, SecondPhoneScreen.class);
          SharedPreferences sp = getSharedPreferences("button1", Context.MODE_PRIVATE);

          String contactId = sp.getString("contactId", "");
          String number = sp.getString("number", "");
          String hasWhatsapp = sp.getString("hasWhatsapp", "");

          intent.putExtra("number", number);
          intent.putExtra("contactId", contactId);
          intent.putExtra("whatsapp", hasWhatsapp);

          startActivity(intent);
      } else {
          Context context = getApplicationContext();
          CharSequence text = "Please select a contact first";
          int duration = Toast.LENGTH_SHORT;
          Toast toast = Toast.makeText(context, text, duration);
          toast.show();
      }
  }

  public void pressQuickCallButton2(View view) {
    if(!quickCallButton2.getText().toString().equals(getResources().getString(R.string.default_quick_call))) {
      Intent intent = new Intent(this, SecondPhoneScreen.class);
      SharedPreferences sp = getSharedPreferences("button2", Context.MODE_PRIVATE);

      String contactId = sp.getString("contactId", "");
      String number = sp.getString("number", "");
      String hasWhatsapp = sp.getString("hasWhatsapp", "");

      intent.putExtra("number", number);
      intent.putExtra("contactId", contactId);
      intent.putExtra("whatsapp", hasWhatsapp);

      startActivity(intent);
    } else {
      Context context = getApplicationContext();
      CharSequence text = "Please select a contact first";
      int duration = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }
  }

  public void pressQuickCallButton3(View view) {
    if(!quickCallButton3.getText().toString().equals(getResources().getString(R.string.default_quick_call))) {
      Intent intent = new Intent(this, SecondPhoneScreen.class);
      SharedPreferences sp = getSharedPreferences("button3", Context.MODE_PRIVATE);

      String contactId = sp.getString("contactId", "");
      String number = sp.getString("number", "");
      String hasWhatsapp = sp.getString("hasWhatsapp", "");

      intent.putExtra("number", number);
      intent.putExtra("contactId", contactId);
      intent.putExtra("whatsapp", hasWhatsapp);

      startActivity(intent);
    } else {
      Context context = getApplicationContext();
      CharSequence text = "Please select a contact first";
      int duration = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }
  }

  public void pressQuickCallButton4(View view) {
    if(!quickCallButton4.getText().toString().equals(getResources().getString(R.string.default_quick_call))) {
      Intent intent = new Intent(this, SecondPhoneScreen.class);
      SharedPreferences sp = getSharedPreferences("button4", Context.MODE_PRIVATE);

      String contactId = sp.getString("contactId", "");
      String number = sp.getString("number", "");
      String hasWhatsapp = sp.getString("hasWhatsapp", "");

      intent.putExtra("number", number);
      intent.putExtra("contactId", contactId);
      intent.putExtra("whatsapp", hasWhatsapp);

      startActivity(intent);
    } else {
      Context context = getApplicationContext();
      CharSequence text = "Please select a contact first";
      int duration = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }
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
              quickCallButton1.setText(tmpInfo.displayName);
              addButton1.setText(R.string.minus_sign);

              SharedPreferences sp1 = getSharedPreferences("button1", MODE_PRIVATE);
              SharedPreferences.Editor editor1 = sp1.edit();
              editor1.putString("displayName", tmpInfo.displayName);
              editor1.putString("number", tmpInfo.number);
              editor1.putString("hasWhatsapp", tmpInfo.hasWhatsapp);
              editor1.apply();
              break;
        case 2:
          quickCallButton2.setText(tmpInfo.displayName);
          addButton2.setText(R.string.minus_sign);

          SharedPreferences sp2 = getSharedPreferences("button2", MODE_PRIVATE);
          SharedPreferences.Editor editor2 = sp2.edit();
          editor2.putString("displayName", tmpInfo.displayName);
          editor2.putString("number", tmpInfo.number);
          editor2.putString("hasWhatsapp", tmpInfo.hasWhatsapp);
          editor2.apply();
          break;
        case 3:
          quickCallButton3.setText(tmpInfo.displayName);
          addButton3.setText(R.string.minus_sign);

          SharedPreferences sp3 = getSharedPreferences("button3", MODE_PRIVATE);
          SharedPreferences.Editor editor3 = sp3.edit();
          editor3.putString("displayName", tmpInfo.displayName);
          editor3.putString("number", tmpInfo.number);
          editor3.putString("hasWhatsapp", tmpInfo.hasWhatsapp);
          editor3.apply();
          break;
        case 4:
          quickCallButton4.setText(tmpInfo.displayName);
          addButton4.setText(R.string.minus_sign);

          SharedPreferences sp4 = getSharedPreferences("button4", MODE_PRIVATE);
          SharedPreferences.Editor editor4 = sp4.edit();
          editor4.putString("displayName", tmpInfo.displayName);
          editor4.putString("number", tmpInfo.number);
          editor4.putString("hasWhatsapp", tmpInfo.hasWhatsapp);
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

    SharedPreferences sp1 = getSharedPreferences("button1", Context.MODE_PRIVATE);
    if(sp1.contains("displayName")){
        quickCallButton3.setText(sp1.getString("displayName", ""));
        addButton2.setText(R.string.minus_sign);
    }

    SharedPreferences sp2 = getSharedPreferences("button2", Context.MODE_PRIVATE);
    if(sp2.contains("displayName")){
        quickCallButton4.setText(sp2.getString("displayName", ""));
        addButton4.setText(R.string.minus_sign);
    }

    SharedPreferences sp3 = getSharedPreferences("button3", Context.MODE_PRIVATE);
    if(sp3.contains("displayName")){
        quickCallButton3.setText(sp3.getString("displayName", ""));
        addButton3.setText(R.string.minus_sign);
    }

    SharedPreferences sp4 = getSharedPreferences("button4", Context.MODE_PRIVATE);
    if(sp4.contains("displayName")){
        quickCallButton4.setText(sp4.getString("displayName", ""));
        addButton4.setText(R.string.minus_sign);
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