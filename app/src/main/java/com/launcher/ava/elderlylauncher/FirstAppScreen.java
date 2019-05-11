package com.launcher.ava.elderlylauncher;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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


  public void setFavouriteApps() {
    final TextView textApp1 = (TextView) findViewById(R.id.textApp1);
    textApp1.setText(AppFrequencyList.getInstance().getHit(0).label);
    final Button app1 = (Button) findViewById(R.id.app1);
    app1.setBackground(AppFrequencyList.getInstance().getHit(0).icon);

    final TextView textApp2 = (TextView) findViewById(R.id.textApp2);
    textApp2.setText(AppFrequencyList.getInstance().getHit(1).label);
    final Button app2 = (Button) findViewById(R.id.app2);
    app2.setBackground(AppFrequencyList.getInstance().getHit(1).icon);

    final TextView textApp3 = (TextView) findViewById(R.id.textApp3);
    textApp3.setText(AppFrequencyList.getInstance().getHit(2).label);
    final Button app3 = (Button) findViewById(R.id.app3);
    app3.setBackground(AppFrequencyList.getInstance().getHit(2).icon);

    final TextView textApp4 = (TextView) findViewById(R.id.textApp4);
    textApp4.setText(AppFrequencyList.getInstance().getHit(3).label);
    final Button app4 = (Button) findViewById(R.id.app4);
    app4.setBackground(AppFrequencyList.getInstance().getHit(3).icon);
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

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case R.id.search_m:
//        //start search dialog
////        onSearchRequested();
//        return true;
//      default:
//        return super.onOptionsItemSelected(item);
//    }
//  }

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
