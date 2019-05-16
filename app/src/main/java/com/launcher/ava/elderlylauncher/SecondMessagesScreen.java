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

  public void pressCall(View view) {
    switch (view.getId()) {
      case R.id.textRegCall:
        sendTextMessage();
        break;
      case R.id.textSecondCall:
        if (this.secondOptionFlag.equals("WHATSAPP")) {
          sendWhatsAppMessage();
        }

        if (this.secondOptionFlag.equals("VIBER")) {
          sendViberMessage();
        }
        break;
      case R.id.textThirdCall:
        sendViberMessage();
        break;
    }
  }

  private void sendViberMessage() {
    String viberRoot = "viber://chat?number=%2B";
    String uriViberapp = viberRoot+this.contactInfo.number;

    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
    intentWhatsapp.setData(Uri.parse(uriViberapp));
    startActivity(intentWhatsapp);
    finish();
  }

  private void sendWhatsAppMessage() {
    String whatsAppRoot = "http://api.whatsapp.com/";
    String number = "send?phone="+ this.contactInfo.number;
    String uriWhatsapp = whatsAppRoot+number;

    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
    intentWhatsapp.setData(Uri.parse(uriWhatsapp));
    startActivity(intentWhatsapp);
    finish();
  }

  private void sendTextMessage() {
    String uriRegMsg = "smsto:" + this.contactInfo.number;
    Intent intentRegMsg = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriRegMsg));
    intentRegMsg.putExtra("sms_body", "");
    intentRegMsg.putExtra("compose_mode", true);
    startActivity(intentRegMsg);
    finish();
  }

  public void doNothing(View view) {
  }
}