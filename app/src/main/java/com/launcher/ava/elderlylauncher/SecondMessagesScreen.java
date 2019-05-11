package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.launcher.ava.utilities.ContactInfo;

public class SecondMessagesScreen extends AppCompatActivity {

  private ContactInfo contactInfo = new ContactInfo();

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
    setContentView(R.layout.activity_second_messages_screen);

    Intent thisIntent = getIntent();
    this.contactInfo.displayName = thisIntent.getStringExtra("displayName");
    this.contactInfo.number = thisIntent.getStringExtra("number");
    this.contactInfo.whatsappVoiceId = thisIntent.getStringExtra("whatsappVoiceId");
    this.contactInfo.skypeVoiceId = thisIntent.getStringExtra("skypeVoiceId");
    this.contactInfo.viberVoiceId = thisIntent.getStringExtra("viberVoiceId");

    // get + before country code, replace 00 if needed
    if(this.contactInfo.number.substring(0,2).equals("00")) {
      this.contactInfo.number=
        this.contactInfo.number.replaceFirst("00", "+");
    }

    this.title = findViewById(R.id.textSecondMessagesScreenMessages);
    String s = title.getText().toString() + "\n" + this.contactInfo.displayName;
    title.setText(s);

    this.whiteBlock = findViewById(R.id.cLayoutSecondMessagesScreenWhiteBlock);

    setViews();
  }

  public void setViews() {

    LayoutParams params = (LayoutParams) this.whiteBlock.getLayoutParams();

    this.callOption = findViewById(R.id.textRegMessage);

    if (!this.contactInfo.whatsappVoiceId.equals("NONE")) {
      this.whatsappVoiceOption = findViewById(R.id.textSecondMessage);
      this.whatsappVoiceOption.setText(getString(R.string.using_whatsapp));
      this.whatsappVoiceImage = findViewById(R.id.imageSecondMessage);
      this.whatsappVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_whatsapp));

      this.secondOptionFlag = "WHATSAPP";
      if (!this.contactInfo.viberVoiceId.equals("NONE")) {
        this.viberVoiceOption = findViewById(R.id.textThirdMessage);
        this.viberVoiceOption.setText(getString(R.string.using_viber));

        this.viberVoiceImage = findViewById(R.id.imageThirdMessage);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        params.topToTop = R.id.secondMessagesScreenGuide4;
      } else {
        params.topToTop = R.id.secondMessagesScreenGuide3;
      }

    } else {
      if (!this.contactInfo.viberVoiceId.equals("NONE")) {
        this.viberVoiceOption = findViewById(R.id.textSecondMessage);
        this.viberVoiceOption.setText(getString(R.string.using_viber));
        this.viberVoiceImage = findViewById(R.id.imageSecondMessage);
        this.viberVoiceImage.setImageDrawable(getDrawable(R.drawable.ic_viber));
        this.secondOptionFlag = "VIBER";
        params.topToTop = R.id.secondMessagesScreenGuide3;
      } else {
        params.topToTop = R.id.secondMessagesScreenGuide2;
      }
    }
  }

  public void sendMessage() {
    // Permission has already been granted
    String uri= "smsto:"+this.contactInfo.number;
    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
    intent.putExtra("sms_body", "");
    intent.putExtra("compose_mode", true);
    startActivity(intent);
    finish();
  }

  public void pressSend(View view) {
    switch (view.getId()) {
      case R.id.textRegMessage:
        String uriRegMsg= "smsto:"+this.contactInfo.number;
        Intent intentRegMsg = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriRegMsg));
        intentRegMsg.putExtra("sms_body", "");
        intentRegMsg.putExtra("compose_mode", true);
        startActivity(intentRegMsg);
        finish();
        break;
      case R.id.textSecondMessage:
        if (this.secondOptionFlag.equals("WHATSAPP")) {
          String whatsAppRoot = "http://api.whatsapp.com/";
          String number = "send?phone="+ this.contactInfo.number;
          String uriWhatsapp = whatsAppRoot+number;

          Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
          intentWhatsapp.setData(Uri.parse(uriWhatsapp));
          startActivity(intentWhatsapp);
        }

        if (this.secondOptionFlag.equals("VIBER")) {
          Uri uri = Uri.parse("tel:" + Uri.encode(contactInfo.number));
          Intent intent = new Intent("android.intent.action.VIEW");
          intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
          intent.setData(uri);
          startActivity(intent);
        }
        break;
      case R.id.textThirdMessage:
        Uri uri = Uri.parse("tel:" + Uri.encode(contactInfo.number));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
        intent.setData(uri);
        startActivity(intent);
        break;
    }
  }

  public void doNothing(View view) {
  }
}