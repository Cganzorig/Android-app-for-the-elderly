package com.launcher.ava.utilities;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class SearchContentProvider extends ContentProvider {

    private static final String STORES = "stores/" + SearchManager.SUGGEST_URI_PATH_QUERY + "/*";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.launcher.ava.utilities.search", STORES, 1);
    }

    private static String[] matrixCursorColumns = {"_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA};

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        String queryType = "";
        switch (uriMatcher.match(uri)) {
            case 1:
                String query = uri.getLastPathSegment().toLowerCase();
                return getSearchResultsCursor(query);
            default:
                return null;
        }
    }

    private MatrixCursor getSearchResultsCursor(String searchString) {
        MatrixCursor searchResults = new MatrixCursor(matrixCursorColumns);
        Object[] mRow = new Object[4];int counterId = 0;

        if (searchString != null) {
            searchString = searchString.toLowerCase();
            final PackageManager pm = getContext().getPackageManager();

            Intent i = new Intent(Intent.ACTION_MAIN, null);
            i.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
            for (ResolveInfo ri : allApps) {
                // If the name of the app contains the query, add to the result
                if (ri.loadLabel(pm).toString().toLowerCase().contains(searchString)) {
                    mRow[0] = "" + counterId++;
                    mRow[1] = ri.loadLabel(pm).toString();
                    mRow[2] = ri.activityInfo.loadIcon(pm);
                    mRow[3] = ri.activityInfo.packageName;

                    searchResults.addRow(mRow);
                }
            }

        }


        return searchResults;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}