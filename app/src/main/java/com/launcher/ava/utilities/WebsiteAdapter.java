package com.launcher.ava.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.launcher.ava.elderlylauncher.R;
import java.util.List;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.MyViewHolder> {

  private List<Website> mDataset;
  private OnItemClick itemClick;

  public WebsiteAdapter(List<Website> listOfWebsites, Context context) {
    this.mDataset = listOfWebsites;
    this.itemClick = ((OnItemClick) context);
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
      .website_row, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.websiteLogo.setImageResource(mDataset.get(position).logoId);
    holder.websiteName.setText(mDataset.get(position).name);
  }


  @Override
  public int getItemCount() {
    return mDataset.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView websiteName;
    ImageView websiteLogo;

    public MyViewHolder(View itemView) {
      super(itemView);
      itemView.setClickable(true);
      itemView.setOnClickListener(this);
      websiteName = itemView.findViewById(R.id.website_name);
      websiteLogo = itemView.findViewById(R.id.website_logo);
    }


    @Override
    public void onClick(View v) {
      itemClick.onItemClicked(mDataset.get(getAdapterPosition()).name);
    }
  }
}
