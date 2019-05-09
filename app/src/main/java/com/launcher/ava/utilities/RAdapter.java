package com.launcher.ava.utilities;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.launcher.ava.elderlylauncher.AppDrawer;
import com.launcher.ava.elderlylauncher.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

  public static final int UNINSTALL_APP_REQUEST = 1;

  private List<AppInfo> appsList;

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;
    public ImageView img;

    //This is the subclass ViewHolder which simply
    //'holds the views' for us to show on each row
    public ViewHolder(View itemView) {
      super(itemView);

      //Finds the views from our row.xml
      textView = itemView.findViewById(R.id.text);
      img = itemView.findViewById(R.id.img);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int pos = getAdapterPosition();
      Context context = v.getContext();

      // get the package name of the app clicked on in the list
      String package_name = appsList.get(pos).packageName.toString();

      if (AppDrawer.isDeleteMode) {
        Intent intent = new Intent(Intent.ACTION_DELETE);

        // remove deleted app from shared preferences
        SharedPreferences sp = context.getSharedPreferences("freqList", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        AppInfoFrequencyPair aip = AppFrequencyList.getInstance().getPairByPackName(package_name);
        editor.remove(aip.getLabel());
        editor.apply();

        // remove deleted app from list of apps
        AppFrequencyList.getInstance().removeApp(package_name);

        package_name = "package:" + package_name;
        intent.setData(Uri.parse(package_name));
        ((Activity) context).startActivityForResult(intent, UNINSTALL_APP_REQUEST);
      }
      else {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(package_name);
        context.startActivity(launchIntent);
        Toast.makeText(v.getContext(), appsList.get(pos).label.toString(), Toast.LENGTH_LONG).show();

        // increment the frequency
        AppInfoFrequencyPair aip = AppFrequencyList.getInstance().getPairByPackName(package_name);
        int freq = aip.getFreq();
        String freqName = aip.getAppInfo().label.toString();
        SharedPreferences sp = context.getSharedPreferences("freqList", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(freqName, Integer.toString(freq));
        editor.apply();
        AppFrequencyList.getInstance().incrementFrequency(package_name);
      }
    }
  }


  public RAdapter(Context c) {

    //This is where we build our list of app details, using the app
    //object we created to store the label, package name and icon
    PackageManager pm = c.getPackageManager();
    this.appsList = new ArrayList<>();

    Intent i = new Intent(Intent.ACTION_MAIN, null);
    i.addCategory(Intent.CATEGORY_LAUNCHER);

    List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
    for (ResolveInfo ri : allApps) {
      if (!ri.activityInfo.packageName.equals("com.launcher.ava.elderlylauncher")) {
        AppInfo app = new AppInfo();
        app.label = ri.loadLabel(pm);
        app.packageName = ri.activityInfo.packageName;
        app.icon = ri.activityInfo.loadIcon(pm);
        this.appsList.add(app);
      }
    }

   Collections.sort(this.appsList);

  }


  @Override
  public void onBindViewHolder(RAdapter.ViewHolder viewHolder, int i) {

    //Here we use the information in the list we created to define the views

    String appLabel = this.appsList.get(i).label.toString();
    String appPackage = this.appsList.get(i).packageName.toString();
    Drawable appIcon = this.appsList.get(i).icon;

    TextView textView = viewHolder.textView;
    textView.setText(appLabel);
    ImageView imageView = viewHolder.img;
    imageView.setImageDrawable(appIcon);
  }


  @Override
  public int getItemCount() {

    //This method needs to be overridden so that Androids knows how many items
    //will be making it into the list

    return this.appsList.size();
  }


  @NonNull
  @Override
  public RAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    //This is what adds the code we've written in here to our target view
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    View view = inflater.inflate(R.layout.row, parent, false);

    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }
}

