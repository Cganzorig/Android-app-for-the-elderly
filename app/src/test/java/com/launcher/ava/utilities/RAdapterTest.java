package com.launcher.ava.utilities;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

// TODO

public class RAdapterTest {

  RAdapter rAdapter;


  @Mock
  Context context;

  @Before
  public void init(){
    rAdapter = new RAdapter(context);

  }

  @Test
  public void onBindViewHolderTest() {
  }

  @Test
  public void getItemCountTest() {
  }

  @Test
  public void onCreateViewHolderTest() {
  }
}