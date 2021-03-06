package com.launcher.ava.elderlylauncher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.launcher.ava.utilities.ContactInfo;

public class SecondPhoneScreen extends AppCompatActivity {

  private ContactInfo contactInfo = new ContactInfo();

  private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
  private static final int MY_PERMISSIONS_REQUEST_WHATSAPP = 2;
  private static final int MY_PERMISSIONS_REQUEST_VIBER = 3;


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

    Intent thisIntent = getIntent();
    this.contactInfo.displayName = thisIntent.getStringExtra("displayName");
    this.contactInfo.number = thisIntent.getStringExtra("number");
    this.contactInfo.whatsappVoiceId = thisIntent.getStringExtra("whatsappVoiceId");
    this.contactInfo.skypeVoiceId = thisIntent.getStringExtra("skypeVoiceId");
    this.contactInfo.viberVoiceId = thisIntent.getStringExtra("viberVoiceId");

    this.title = findViewById(R.id.textSecondPhoneScreenPhone);
    String s = title.getText().toString() + "\n" + this.contactInfo.displayName.toUpperCase();
    title.setText(s);

    this.whiteBlock = findViewById(R.id.cLayoutSecondPhoneScreenWhiteBlock);

    setViews();
  }

  public void setViews() {

    LayoutParams params = (LayoutParams) this.whiteBlock.getLayoutParams();

    this.callOption = findViewById(R.id.textRegCall);

    if (!this.contactInfo.whatsappVoiceId.equals("NONE")) {
      this.whatsappVoiceOption = findViewById(R.id.textSecondCall);
      this.whatsappVoiceOption.setText(getString(R.string.using_whatsapp));
      this.whatsappVoiceImage = findViewById(R.id.imageSecondCall);
      this.whatsappVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_whatsapp));

      this.secondOptionFlag = "WHATSAPP";
      if (!this.contactInfo.viberVoiceId.equals("NONE")) {
        this.viberVoiceOption = findViewById(R.id.textThirdCall);
        this.viberVoiceOption.setText(getString(R.string.using_viber));

        this.viberVoiceImage = findViewById(R.id.imageThirdCall);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        params.topToTop = R.id.secondPhoneScreenGuide4;
      } else {
        params.topToTop = R.id.secondPhoneScreenGuide3;
      }

    } else {
      if (!this.contactInfo.viberVoiceId.equals("NONE")) {
        this.viberVoiceOption = findViewById(R.id.textSecondCall);
        this.viberVoiceOption.setText(getString(R.string.using_viber));
        this.viberVoiceImage = findViewById(R.id.imageSecondCall);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        this.secondOptionFlag = "VIBER";
        params.topToTop = R.id.secondPhoneScreenGuide3;
      } else {
        params.topToTop = R.id.secondPhoneScreenGuide2;
      }
    }
  }

  public void makeCall() {
    // Permission has already been granted
    Intent call = new Intent(Intent.ACTION_CALL);
    call.setData(Uri.parse("tel:" + this.contactInfo.number));
    startActivity(call);
  }

  public void pressCall(View view) {
    switch (view.getId()) {
      case R.id.textRegCall:
        if (ContextCompat.checkSelfPermission(this,
          Manifest.permission.CALL_PHONE)
          != PackageManager.PERMISSION_GRANTED) {

          ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.CALL_PHONE},
            MY_PERMISSIONS_REQUEST_CALL_PHONE);

        } else {
          makeCall();
        }
        break;
      case R.id.textSecondCall:
        if (this.secondOptionFlag.equals("WHATSAPP")) {

          if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
              new String[]{Manifest.permission.CALL_PHONE},
              MY_PERMISSIONS_REQUEST_WHATSAPP);

          } else {
            callWhatsapp();
          }
        }

        if (this.secondOptionFlag.equals("VIBER")) {

          if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
              new String[]{Manifest.permission.CALL_PHONE},
              MY_PERMISSIONS_REQUEST_VIBER);

          } else {
            callViber();
          }

        }
        break;
      case R.id.textThirdCall:
        if (ContextCompat.checkSelfPermission(this,
          Manifest.permission.CALL_PHONE)
          != PackageManager.PERMISSION_GRANTED) {

          ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.CALL_PHONE},
            MY_PERMISSIONS_REQUEST_VIBER);

        } else {
          callViber();
        }
        break;
    }
  }

  private void callWhatsapp() {
    String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";
    String data = "content://com.android.contacts/data/" + this.contactInfo.whatsappVoiceId;
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse(data), mimeString);
    intent.setPackage("com.whatsapp");
    try {
      startActivity(intent);
    } catch (Exception e) {
      //
    }
  }

  private void callViber() {
    String uriString = "content://com.android.contacts/data/" + contactInfo.viberVoiceId;

    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(uriString));
    intent.setPackage("com.viber.voip");
    try {
      startActivity(intent);
    } catch (Exception e) {
      //
    }
  }


  public void doNothing(View view) {
  }


  @Override
  public void onRequestPermissionsResult(int requestCode,
    @NonNull String permissions[], @NonNull int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_CALL_PHONE:
        // permission was granted
        if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          makeCall();
        } else {
          // permission denied
          backHomeIfPermDenied();
        }
        break;
      case MY_PERMISSIONS_REQUEST_WHATSAPP:
        // permission was granted
        if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          callWhatsapp();
        } else {
          // permission denied
          backHomeIfPermDenied();
        }
        break;
      case MY_PERMISSIONS_REQUEST_VIBER:
        // permission was granted
        if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          callWhatsapp();
        } else {
          // permission denied
          backHomeIfPermDenied();
        }
        break;
    }
  }

  private void backHomeIfPermDenied() {
    Intent backToHome = new Intent(this, SecondPhoneScreen.class);
    backToHome.putExtra("displayName", this.contactInfo.displayName);
    backToHome.putExtra("number", this.contactInfo.number);
    backToHome.putExtra("whatsappVoiceId", this.contactInfo.whatsappVoiceId);
    backToHome.putExtra("skypeVoiceId", this.contactInfo.skypeVoiceId);
    backToHome.putExtra("viberVoiceId", this.contactInfo.viberVoiceId);

    startActivity(backToHome);
    finish();
  }
}
