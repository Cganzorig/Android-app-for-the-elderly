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
    websiteList.add( new Website(R.drawable.ic_search,"Google", "https://www.google.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_bbc, "BBC News", "https://www.bbc.co.uk/news/"));
    websiteList.add( new Website(R.drawable.ic_sunny,"Accu Weather", "https://www.accuweather.com"));
    websiteList.add( new Website(R.drawable.ic_crown, "Gov UK", "https://www.gov.uk/"));
    websiteList.add( new Website(R.drawable.ic_news,"Google News", "https://www.news.google.com"));
    websiteList.add( new Website(R.drawable.ic_tripadvisor, "Trip Advisor", "https://www.tripadvisor.co.uk/"));
    websiteList.add( new Website(R.drawable.ic_merriam_webster_logo,"Merriam Webster", "https://www.merriam-webster.com/"));
    websiteList.add( new Website(R.drawable.ic_goodreads_letter_logo, "Goodreads", "https://www.goodreads.com/"));
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
