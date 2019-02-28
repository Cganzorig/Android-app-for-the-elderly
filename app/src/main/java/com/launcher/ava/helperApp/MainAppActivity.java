package com.launcher.ava.helperApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Whatever you want to do with the selected contact name.
                    }
                }
                break;
        }
    }
}
