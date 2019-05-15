package com.launcher.ava.elderlylauncher;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.utilities.AppInfoFrequencyPair;

public class FirstAppScreen extends AppCompatActivity {

  private Menu menu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_first_app_screen);

    setFavouriteApps();

    deflateFakeSearchButton(false);

    Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(myToolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    // Set toolbar click listener
    final int abTitleId = getResources().getIdentifier("action_bar_title", "id", "android");
    findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onOptionsItemSelected(menu.findItem(R.id.search_m));
      }
    });
  }

  public void deflateFakeSearchButton(boolean yes) {
    TextView tv = findViewById(R.id.fakeButton);
    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tv.getLayoutParams();
    if (yes) {
      params.bottomToTop = R.id.firstAppScreenguide1;
    } else {
      params.bottomToTop = R.id.firstAppScreenguide2;
    }
    tv.setLayoutParams(params);
  }

  public void fakeToolbarPress(View v) {
    deflateFakeSearchButton(true);
    menu.performIdentifierAction(R.id.search_m, 0);
  }

  private String setTitle(String label) {

    DisplayMetrics displayMetrics = new DisplayMetrics();

    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    int height = displayMetrics.heightPixels;

    if (height <  600 && label.length() > 10) {

      label = label.substring(0, 10) + "...";
    }

    return label;
  }

  public void setFavouriteApps() {

    final TextView textApp1 = (TextView) findViewById(R.id.textApp1);
    String label1 = setTitle(AppFrequencyList.getInstance().getHit(0).label.toString());
    textApp1.setText(label1);

    final ImageView app1 = findViewById(R.id.app1);
    app1.setImageDrawable(AppFrequencyList.getInstance().getHit(0).icon);

    final TextView textApp2 = (TextView) findViewById(R.id.textApp2);
    String label2 = setTitle(AppFrequencyList.getInstance().getHit(1).label.toString());
    textApp2.setText(label2);

    final ImageView app2 = findViewById(R.id.app2);
    app2.setImageDrawable(AppFrequencyList.getInstance().getHit(1).icon);

    final TextView textApp3 = (TextView) findViewById(R.id.textApp3);
    String label3 = setTitle(AppFrequencyList.getInstance().getHit(2).label.toString());
    textApp3.setText(label3);

    final ImageView app3 =  findViewById(R.id.app3);
    app3.setImageDrawable(AppFrequencyList.getInstance().getHit(2).icon);

    final TextView textApp4 = (TextView) findViewById(R.id.textApp4);
    String label4 = setTitle(AppFrequencyList.getInstance().getHit(3).label.toString());
    textApp4.setText(label4);

    final ImageView app4 = findViewById(R.id.app4);
    app4.setImageDrawable(AppFrequencyList.getInstance().getHit(3).icon);
  }

  @Override
  protected void onResume() {
    super.onResume();
    setFavouriteApps();
    Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(myToolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    deflateFakeSearchButton(false);
  }

  public void onAppClick(View v) {

    switch (v.getId()) {
      case R.id.app1:
        startFreqApp(0);
        break;

      case R.id.app2:
        // Code for button 2 click
        startFreqApp(1);
        break;

      case R.id.app3:
        // Code for button 3 click
        startFreqApp(2);
        break;

      case R.id.app4:
        // Code for button 3 click
        startFreqApp(3);
        break;
    }

  }


  public void onClickGoToAppDrawer(View view) {
    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    v.vibrate(50); // 1000 miliseconds = 1 seconds
    Intent intent = new Intent(this, AppDrawer.class);
    startActivity(intent);
  }


  private void startFreqApp(int i) {
    String packageName = AppFrequencyList.getInstance().getHit(i).packageName.toString();

    // increment the frequency
    AppInfoFrequencyPair aip = AppFrequencyList.getInstance().getPairByPackName(packageName);
    int freq = aip.getFreq();
    String freqName = aip.getAppInfo().label.toString();
    SharedPreferences sp = getSharedPreferences("freqList", MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(freqName, Integer.toString(freq));
    editor.apply();

    AppFrequencyList.getInstance().incrementFrequency(packageName);
    Intent launchIntent =
      getPackageManager().getLaunchIntentForPackage(packageName);
    startActivity(launchIntent);
  }


  public void onResetClick() {

  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    this.menu = menu;

    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    final SearchView searchView = (SearchView) menu.findItem(R.id.search_m).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

    // Disable enter click
    final EditText searchEditText = (EditText) searchView
      .findViewById(android.support.v7.appcompat.R.id.search_src_text);

    // to re-inflate the big fake search button
    searchView.setOnQueryTextFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          deflateFakeSearchButton(false);
        }
      }
    });

    searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH
          || actionId == EditorInfo.IME_NULL) {
          return true;
        }
        return false;
      }
    });
    return true;
  }

  public void launchPlayStore(View view) {
    try {
      Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
      startActivity(launchIntent);
    } catch (Exception e) {
      Toast.makeText(this, "You don't have app store!", Toast.LENGTH_SHORT).show();
    }
  }

  public void launchUninstallApp(View view) {
    Intent intent = new Intent(this, AppDrawer.class);
    intent.putExtra("deleteMode", 1);
    startActivity(intent);
  }
}
