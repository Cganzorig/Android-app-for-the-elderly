package com.launcher.ava.elderlylauncher;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    TextView tv1 = (TextView)findViewById(R.id.torchText);
    tv1.setText(getResources().getString(R.string.turn_torch_on));
    ConstraintLayout torchLayout = (ConstraintLayout)findViewById(R.id.cLayoutTorch);
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

      TextView tv1 = (TextView)findViewById(R.id.torchText);
      ConstraintLayout torchLayout = (ConstraintLayout)findViewById(R.id.cLayoutTorch);
      if (NoobCameraManager.getInstance().isFlashOn()) {
        tv1.setText(getResources().getString(R.string.turn_torch_off));
        torchLayout.setBackgroundResource(R.color.torchOnYellow);
      }
      else {
        tv1.setText(getResources().getString(R.string.turn_torch_on));
        torchLayout.setBackgroundResource(R.color.lightGray);
      }

      NoobCameraManager.getInstance().release();
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }

  }

  public void launchAddContact(View view) {
    Intent intent = new Intent(Intent.ACTION_INSERT,
      ContactsContract.Contacts.CONTENT_URI);
    startActivity(intent);
  }

  public void launchCalculator(View view) {
    ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();

    final PackageManager pm = getPackageManager();
    List<PackageInfo> packs = pm.getInstalledPackages(0);
    for (PackageInfo pi : packs) {
      if (pi.packageName.toString().toLowerCase().contains("calcul")) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("appName", pi.applicationInfo.loadLabel(pm));
        map.put("packageName", pi.packageName);
        items.add(map);
      }
    }

    if (items.size() >= 1) {
      String packageName = (String) items.get(0).get("packageName");
      Intent i = pm.getLaunchIntentForPackage(packageName);
      if (i != null) {
        startActivity(i);
      }
    } else {
      // No calculator app found
    }

  }

  public void launchSetTimer(View view) {
    Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_TIMER);
    openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(openClockIntent);
  }

  public void launchPlayStore(View view) {
    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
    startActivity(launchIntent);
  }

  public void launchUninstallApp(View view) {
    // Need to launch app drawer view, get package name and use in the code below to uninstall.
    // App drawer not working currently, so have left for later

//        Intent intent = new Intent(Intent.ACTION_DELETE);
//        intent.setData(Uri.parse("package:com.instagram.android"));
//        startActivity(intent);
  }

}
