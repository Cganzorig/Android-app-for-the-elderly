package com.launcher.ava.elderlylauncher;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code
  private int CALL_PHONE_PERMISSION_CODE = 1;
  private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
  private String number;

  private Runnable show_toast = new Runnable() {
    public void run() {
      Toast.makeText(PhoneScreen.this, "My Toast message", Toast.LENGTH_SHORT)
              .show();
    }
  };


  @RequiresPermission(Manifest.permission.CALL_PHONE)
  public static final String ACTION_CALL = "android.intent.action.CALL";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_phone_screen);
  }


  public void launchContacts(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds

    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
    if (requestCode == PICK_CONTACT_REQUEST) {
      // Make sure the request was successful
      if (resultCode == RESULT_OK) {
        getPhoneNumber(data);
        makePhoneCall();
      }
    }
  }

  private void getPhoneNumber(Intent data) {
    Uri contactUri = data.getData();
    // We only need the NUMBER column, because there will be only one row in the result
    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

    // Perform the query on the contact to get the NUMBER column
    // We don't need a selection or sort order (there's only one result for the given URI)
    // CAUTION: The query() method should be called from a separate thread to avoid blocking
    // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
    // Consider using <code><a href="/reference/android/content/CursorLoader.html">CursorLoader</a></code> to perform the query.

    Cursor cursor = getContentResolver()
            .query(contactUri, projection, null, null, null);
    cursor.moveToNext();
//         Retrieve the phone number from the NUMBER column
    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    this.number = cursor.getString(column);
  }

  private void makePhoneCall() {
    TextView tv1 = (TextView) findViewById(R.id.phoneNumber);
    tv1.setText(number);

    // convert number to contact id so we can use the hasWhatsapp function
    String cid = convertNumberToID(number);

    if (hasWhatsApp(cid)) {
      tv1.setText("HAS WHATSAPP");
    }

    if (ContextCompat.checkSelfPermission(PhoneScreen.this,
            Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_CODE);

//          // Permission is not granted
//          // Should we show an explanation?
//          if (ActivityCompat.shouldShowRequestPermissionRationale(PhoneScreen.this,
//                  Manifest.permission.CALL_PHONE)) {
//            // Show an explanation to the user *asynchronously* -- don't block
//            // this thread waiting for the user's response! After the user
//            // sees the explanation, try again to request the permission.
//          } else {
//            // No explanation needed; request the permission
//            ActivityCompat.requestPermissions(PhoneScreen.this,
//                    new String[]{Manifest.permission.CALL_PHONE},
//                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

      // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
      // app-defined int constant. The callback method gets the
      // result of the request.
    } else {
      // Permission has already been granted
      Intent call = new Intent(Intent.ACTION_CALL);
      call.setData(Uri.parse("tel:" + this.number));
      startActivity(call);
    }

  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
        // permission was granted
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Intent call = new Intent(Intent.ACTION_CALL);
          call.setData(Uri.parse("tel:" + this.number));
          startActivity(call);

        } else {
          // permission denied
          // Could display information here why you need permission
        }
        return;
      }
    }
  }

  private boolean hasWhatsApp(String contact_id) {
    String[] projection = new String[] { ContactsContract.RawContacts._ID };
    String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
    String[] selectionArgs = new String[] { contact_id, "com.whatsapp" };
    Cursor cursor = PhoneScreen.this.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
    boolean contactHasWhatsApp = cursor.moveToNext();
    return contactHasWhatsApp;
  }

  private String convertNumberToID(String number) {
    ContentResolver contentResolver = PhoneScreen.this.getContentResolver();

    Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

    String[] projection = new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

    Cursor cursor =
            contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    null);

    cursor.moveToNext();
    String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
    cursor.close();
    return contactId;
    }
}