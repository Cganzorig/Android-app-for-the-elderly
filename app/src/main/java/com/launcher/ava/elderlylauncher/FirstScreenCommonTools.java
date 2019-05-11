package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.noob.noobcameraflash.managers.NoobCameraManager;

public class FirstScreenCommonTools extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Create NoobCameraManager Instance to control torch
    try {
      NoobCameraManager.getInstance().init(this);
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
    setContentView(R.layout.activity_common_tools);
    getWindow().getDecorView().setBackgroundColor(Color.WHITE);

  }

  public void changeTorchBoxToOff() {
    TextView tv1 = (TextView) findViewById(R.id.torchText);
    tv1.setText(getResources().getString(R.string.turn_torch_on));
    ConstraintLayout torchLayout = (ConstraintLayout) findViewById(R.id.cLayoutTorch);
    torchLayout.setBackgroundResource(R.color.lightGray);
  }

  public void turnTorchOff() {
    try {
      changeTorchBoxToOff();
      NoobCameraManager.getInstance().turnOffFlash();
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onPause() {
    turnTorchOff();
    super.onPause();
  }

  @Override
  public void onStop() {
    turnTorchOff();
    super.onStop();
  }


  public void launchDefaultCamera(View view) {
    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    turnTorchOff();
    startActivity(
      getPackageManager().getLaunchIntentForPackage(
        intent.resolveActivity(getPackageManager()).getPackageName()));
  }

  public void launchAlarmSettings(View view) {
    Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
    openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(openClockIntent);
  }

  public void toggleFlash(View view) {
    try {
      NoobCameraManager.getInstance().toggleFlash();

      TextView tv1 = (TextView) findViewById(R.id.torchText);
      ConstraintLayout torchLayout = (ConstraintLayout) findViewById(R.id.cLayoutTorch);
      if (NoobCameraManager.getInstance().isFlashOn()) {
        tv1.setText(getResources().getString(R.string.turn_torch_off));
        torchLayout.setBackgroundResource(R.color.torchOnColor);
      } else {
        tv1.setText(getResources().getString(R.string.turn_torch_on));
        torchLayout.setBackgroundResource(R.color.lightGray);
      }

      NoobCameraManager.getInstance().release();
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }

  }


  public void launchAddContact(View view) {

    try {
      Intent intent = new Intent(Intent.ACTION_INSERT,
        ContactsContract.Contacts.CONTENT_URI);
      startActivity(intent);
    } catch (Exception e) {
      //
    }

  }

  public void launchSettings(View view) {
    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
  }

  public void launchPlayStore(View view) {
    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
    startActivity(launchIntent);
  }

}
