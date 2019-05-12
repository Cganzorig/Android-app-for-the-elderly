package com.launcher.ava.utilities;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.plugins.MockitoLogger;
import org.mockito.stubbing.Stubber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO

public class RecyclerListTest {
  RecyclerList recyclerList;
  AppCompatActivity appCompatActivity;


  @Before
  public void init() throws Exception{
  }

  @Test
  public void onCreateTest() {
  }
}