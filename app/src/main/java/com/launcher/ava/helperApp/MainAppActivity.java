package com.launcher.ava.helperApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.launcher.ava.elderlylauncher.R;

public class MainAppActivity extends AppCompatActivity {

    static final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        // Change the background of the app drawer to white
        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
    }


    public void launchContacts(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    public void launchMessages(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"));
        startActivity(intent);
    }

    public void launchChrome(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Uri webpage = Uri.parse("https://duckduckgo.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        // String packName = "com.android.chrome";
        // Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packName);
        if(intent!= null){
          startActivity(intent);
        }
    }


    public void launchAppScreen(View view) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50); // 1000 miliseconds = 1 seconds
        Intent intent = new Intent(this, AppScreen.class);
        startActivity(intent);
    }
}
