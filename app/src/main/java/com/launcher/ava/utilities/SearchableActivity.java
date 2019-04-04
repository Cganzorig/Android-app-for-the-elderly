package com.launcher.ava.utilities;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import com.launcher.ava.elderlylauncher.R;
import com.launcher.ava.utilities.RAdapter;

public class SearchableActivity extends ListActivity {

  private RAdapter radapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_searchable);

    RecyclerView recyclerView = findViewById(R.id.search_RView);
    radapter = new RAdapter(this);
    recyclerView.setAdapter(radapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // The search intent contains the search query. Call the handle intent function to get
    // the query
    handleIntent(getIntent());
  }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the options menu from XML
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//
//        // Get the SearchView and set the searchable configuration
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//
//        return true;
//    }

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


  /* Using list activity - From Android Developer*/
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.custom_list_activity_view);

        // Query for all people contacts using the Contacts.People convenience class.
        // Put a managed wrapper around the retrieved cursor so we don't have to worry about
        // requerying or closing it as the activity changes state.
        mCursor = this.getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(mCursor);

        // Now create a new list adapter bound to the cursor.
        // SimpleListAdapter is designed for binding to a Cursor.
        ListAdapter adapter = new SimpleCursorAdapter(
                this, // Context.
                android.R.layout.two_line_list_item,  // Specify the row template to use (here, two columns bound to the two retrieved cursor
                rows).
                mCursor,                                              // Pass in the cursor to bind to.
        new String[] {People.NAME, People.COMPANY},           // Array of cursor columns to bind to.
                new int[] {android.R.id.text1, android.R.id.text2});  // Parallel array of which template objects to bind to those columns.

        // Bind to our new adapter.
        setListAdapter(adapter);
    }
    */
}
