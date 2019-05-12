package com.launcher.ava.utilities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.launcher.ava.elderlylauncher.R.layout.activity_app_drawer;
import static org.junit.Assert.*;

public class SearchAdapterTest {

  private SearchAdapter searchAdapter;
  private List<String> dataList;

  @Mock
  private ViewGroup viewGroup;
  private Context mContext;
  private int searchResultItemLayout;

  @Before
  public void init(){
    dataList = new ArrayList<String>();
    dataList.add("A");
    dataList.add("B");
    dataList.add("C");
    dataList.add("a");

    searchAdapter = new SearchAdapter(mContext, searchResultItemLayout, dataList);


  }

  @Test
  public void getCountTest() {
    assertEquals(4, searchAdapter.getCount());
  }

  @Test
  public void getItemTest() {
    assertEquals("a", searchAdapter.getItem(3));
  }

  @Test
  public void getViewTest() {

  }
}