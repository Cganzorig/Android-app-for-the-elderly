package com.launcher.ava.utilities;

import com.launcher.ava.elderlylauncher.R;
import java.util.ArrayList;
import java.util.List;

public class WebsiteDatabase {

  private static List<Website> websiteList;
  private static WebsiteDatabase instance = new WebsiteDatabase();

  private WebsiteDatabase() {
    websiteList = new ArrayList<>();
    websiteList.add( new Website(R.drawable.ic_amazon,"Amazon", "https://www.amazon.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_wikipedia_logo, "Wikipedia", "https://www.wikipedia.org/"));
    websiteList.add( new Website(R.drawable.ic_amazon,"Amazon", "https://www.amazon.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_wikipedia_logo, "Wikipedia", "https://www.wikipedia.org/"));
    websiteList.add( new Website(R.drawable.ic_amazon,"Amazon", "https://www.amazon.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_wikipedia_logo, "Wikipedia", "https://www.wikipedia.org/"));
    websiteList.add( new Website(R.drawable.ic_amazon,"Amazon", "https://www.amazon.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_wikipedia_logo, "Wikipedia", "https://www.wikipedia.org/"));
    websiteList.add( new Website(R.drawable.ic_amazon,"Amazon", "https://www.amazon.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_wikipedia_logo, "Wikipedia", "https://www.wikipedia.org/"));
  }

  public static List<Website> getInstance() {return websiteList;}

  public static String getUrl(String webName) {
    for(Website it: websiteList) {
      if(it.name.equals(webName)) {
        return it.url;
      }
    }
    return "www.google.com";
  }

}
