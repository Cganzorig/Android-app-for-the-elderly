package com.launcher.ava.elderlylauncher;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.launcher.ava.utilities.ContactInfo;

public class SecondPhoneScreen extends AppCompatActivity {

  private ContactInfo contactInfo = new ContactInfo();

  private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
  TextView title;
  TextView callOption;
  TextView whatsappVoiceOption = null;
  TextView viberVoiceOption = null;
  ImageView whatsappVoiceImage = null;
  ImageView viberVoiceImage = null;
  ConstraintLayout whiteBlock;
  String secondOptionFlag;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_phone_screen);

    // get additional params passed from first phone screen
    Intent thisIntent = getIntent();
    this.contactInfo.displayName = thisIntent.getStringExtra("displayName");
    this.contactInfo.number = thisIntent.getStringExtra("number");
    this.contactInfo.whatsappVoiceId = thisIntent.getStringExtra("whatsappVoiceId");
    this.contactInfo.skypeVoiceId = thisIntent.getStringExtra("skypeVoiceId");
    this.contactInfo.viberVoiceId = thisIntent.getStringExtra("viberVoiceId");

    this.title = findViewById(R.id.textSecondPhoneScreenPhone);
    String s = title.getText().toString()  + " TO " + this.contactInfo.displayName;
    title.setText(s);

    this.whiteBlock = findViewById(R.id.cLayoutSecondPhoneScreenWhiteBlock);

    setViews();
  }

  public void setViews() {

    LayoutParams params = (LayoutParams) this.whiteBlock.getLayoutParams();

    this.callOption = findViewById(R.id.textRegCall);

    if (!this.contactInfo.whatsappVoiceId.equals("NONE")) {
      this.whatsappVoiceOption = (TextView) findViewById(R.id.textSecondCall);
      this.whatsappVoiceOption.setText(getString(R.string.using_whatsapp));
      this.whatsappVoiceImage = (ImageView) findViewById(R.id.imageSecondCall);
      this.whatsappVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_whatsapp));

      this.secondOptionFlag = "WHATSAPP";
      if (!this.contactInfo.whatsappVoiceId.equals("NONE")) {
        this.viberVoiceOption = (TextView) findViewById(R.id.textThirdCall);
        this.viberVoiceOption.setText(getString(R.string.using_viber));

        this.viberVoiceImage = (ImageView) findViewById(R.id.imageThirdCall);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        params.topToTop = R.id.secondPhoneScreenGuide4;
      } else {
        params.topToTop = R.id.secondPhoneScreenGuide3;
      }

    } else {
      if (!this.contactInfo.viberVoiceId.equals("NONE")) {
        this.viberVoiceOption = (TextView) findViewById(R.id.textSecondCall);
        this.viberVoiceOption.setText(getString(R.string.using_viber));
        this.viberVoiceImage = (ImageView) findViewById(R.id.imageSecondCall);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        this.secondOptionFlag = "VIBER";
        params.topToTop = R.id.secondPhoneScreenGuide3;
      } else {
        params.topToTop = R.id.secondPhoneScreenGuide2;
      }
    }
  }

  public void pressCall(View view) {
    switch (view.getId()) {
      case R.id.cLayoutRegCall:
        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

          int CALL_PHONE_PERMISSION_CODE = 1;
          ActivityCompat.requestPermissions(this,
              new String[] {Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_CODE);
        } else {
          // Permission has already been granted
          Intent call = new Intent(Intent.ACTION_CALL);
          call.setData(Uri.parse("tel:" + this.contactInfo.number));
          startActivity(call);
        }
        break;
      case R.id.cLayoutSecondCall:
        if(this.secondOptionFlag.equals("WHATSAPP")) {
          String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
          String data = "content://com.android.contacts/data/" + this.contactInfo.whatsappVoiceId;
          Intent intent = new Intent();
          intent.setAction(Intent.ACTION_VIEW);
          intent.setDataAndType(Uri.parse(data), mimeString);
          intent.setPackage("com.whatsapp");
          startActivity(intent);
        }

        if(this.secondOptionFlag.equals("VIBER")) {
          Uri uri = Uri.parse("tel:" + Uri.encode(contactInfo.number));
          Intent intent = new Intent("android.intent.action.VIEW");
          intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
          intent.setData(uri);
          startActivity(intent);
        }
        break;
      case R.id.cLayoutThirdCall:
        Uri uri = Uri.parse("tel:" + Uri.encode(contactInfo.number));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
        intent.setData(uri);
        startActivity(intent);
        break;
    }
  }

  public void doNothing(View view) {}

  public void callWhatsApp(View view) {

    if(!this.contactInfo.whatsappVoiceId.equals("NONE")) {

      String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
      String data = "content://com.android.contacts/data/" + this.contactInfo.whatsappVoiceId;
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
      call.setData(Uri.parse("tel:" + this.contactInfo.number));
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
          call.setData(Uri.parse("tel:" + this.contactInfo.number));
          startActivity(call);

        } else {
          // permission denied
          // Could display information here why you need permission
        }
      }
    }
  }
}
