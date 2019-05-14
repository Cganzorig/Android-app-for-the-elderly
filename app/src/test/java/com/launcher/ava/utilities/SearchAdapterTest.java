package com.launcher.ava.utilities;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.launcher.ava.elderlylauncher.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.launcher.ava.elderlylauncher.R.layout.activity_app_drawer;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class SearchAdapterTest {

  private SearchAdapter searchAdapter;
  private List<String> dataList;
  private View fakeview;
  private TextView fakeTv;
  ViewGroup viewGroup;

  @Before
  public void init(){
    viewGroup= Mockito.mock(ViewGroup.class);
    Context mContext= Mockito.mock(Context.class);
    fakeview = Mockito.mock(View.class);
    fakeTv = Mockito.mock(TextView.class);
    ClipData.Item fakeItem = Mockito.mock(ClipData.Item.class);
    LayoutInflater layoutInflater = Mockito.mock(LayoutInflater.class);



    dataList = new ArrayList<String>();
    dataList.add("A");
    dataList.add("B");
    dataList.add("C");
    dataList.add("a");

    searchAdapter = new SearchAdapter(mContext, 3, dataList);

    Mockito.when(viewGroup.getContext()).thenReturn(mContext);
    Mockito.when(layoutInflater.inflate(3, viewGroup,false)).thenReturn(fakeview);
    Mockito.when((TextView) fakeview.findViewById(anyInt())).thenReturn(fakeTv);
    Mockito.doNothing().when(fakeTv).setText(anyInt());




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
  public void getViewTest(){
    View testview = searchAdapter.getView(3, fakeview, viewGroup);
    verify(fakeTv).setText("a");
    View view = null;

  }
}