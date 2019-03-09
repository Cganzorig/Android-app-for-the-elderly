package com.launcher.ava.elderlylauncher;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneScreen extends AppCompatActivity {

  static final int PICK_CONTACT_REQUEST = 1;  // The request code

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
        String number = cursor.getString(column);

        TextView tv1 = (TextView)findViewById(R.id.phoneNumber);
        tv1.setText(number);

        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        startActivity(surf);
      }
    }
  }




}
