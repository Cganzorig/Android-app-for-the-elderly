package com.launcher.ava.helperApp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.launcher.ava.elderlylauncher.AppDrawer;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.AppFrequencyList;
import com.launcher.ava.utilities.RAdapter;
import com.launcher.ava.utilities.RemoveStatusBar;

public class AppScreen extends AppCompatActivity implements SearchView.OnQueryTextListener {

  private RAdapter radapter;
  private SearchView searchView;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RemoveStatusBar.remove(this);

    setContentView(R.layout.activity_app_screen);

    setFavouriteApps();

    /* Below is the search function */
    searchView = (SearchView) findViewById(R.id.searchView3);
    searchView.setQueryHint("Enter search");

    recyclerView = (RecyclerView) findViewById(R.id.searcRecyView);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        radapter = new RAdapter(AppScreen.this);
        radapter.reuturnQueryResults(query);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppScreen.this));
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
  }

  public void setFavouriteApps() {
    setContentView(R.layout.activity_app_screen);
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
  }

  public void onAppClick(View v) {

//        v.getBackground().setAlpha(100);

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
    AppFrequencyList.getInstance().incrementFrequency(packageName);
    Intent launchIntent =
      getPackageManager().getLaunchIntentForPackage(packageName);
    startActivity(launchIntent);
  }


  public void onResetClick() {

  }


  /* Search functions */
  public void onNewIntent(Intent intent) {
    setIntent(intent);
    handleIntent(intent);
  }

  public void onListItemClick(ListView l,
    View v, int position, long id) {
    // call detail activity for clicked entry
  }

  private void handleIntent(Intent intent) {
    // Get the intent, verify the action and get the query
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query =
        intent.getStringExtra(SearchManager.QUERY);
      doSearch(query);
    }
  }

  private void doSearch(String queryStr) {
    // Get all applications
    radapter.reuturnQueryResults(queryStr);
  }


  @Override
  public boolean onQueryTextSubmit(String query) {

    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    String text = newText;
    doSearch(newText);
    return false;
  }


}
