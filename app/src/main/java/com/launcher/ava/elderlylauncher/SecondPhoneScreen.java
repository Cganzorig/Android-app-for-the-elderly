package com.launcher.ava.elderlylauncher;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SecondPhoneScreen extends AppCompatActivity {

  String number;
  String whatsappVoiceId;
  private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_phone_screen);

    // get additional params passed from first phone screen
    Intent thisIntent = getIntent();
    this.number = thisIntent.getStringExtra("number");
    this.whatsappVoiceId = thisIntent.getStringExtra("whatsappVoiceId");
  }

  public void callWhatsApp(View view) {

    if(!this.whatsappVoiceId.equals("NONE")) {

      String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
      String data = "content://com.android.contacts/data/" + this.whatsappVoiceId;
      Intent intent = new Intent();
      intent.setAction(Intent.ACTION_VIEW);
      intent.setDataAndType(Uri.parse(data), mimeString);
      intent.setPackage("com.whatsapp");
      startActivity(intent);

    } else {

      Context context = getApplicationContext();
      CharSequence text = "You or your contact don't have WhatsApp installed";
      int duration = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    }

  }

  public void makePhoneCall(View view) {

    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {

      int CALL_PHONE_PERMISSION_CODE = 1;
      ActivityCompat.requestPermissions(this,
          new String[] {Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_CODE);
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
      }
    }
  }
}
