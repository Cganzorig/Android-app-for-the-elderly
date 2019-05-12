package com.launcher.ava.utilities;

import android.app.Activity;
import android.view.Window;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;

public class RemoveStatusBarTest {

  RemoveStatusBar removeStatusBar;


  Activity activity = Mockito.mock(Activity.class);
  Window window = Mockito.mock(Window.class);


  @Before
  public void init(){

    removeStatusBar = new RemoveStatusBar();
    Mockito.when(activity.requestWindowFeature(anyInt())).thenReturn(true);
    Mockito.when(activity.getWindow()).thenReturn(window);
    Mockito.doNothing().when(window).addFlags(anyInt());


  }
  @Test
  public void remove() {
    removeStatusBar.remove(activity);
    Mockito.verify(activity).requestWindowFeature(anyInt());
  }
}