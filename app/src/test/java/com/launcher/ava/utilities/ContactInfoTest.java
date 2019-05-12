package com.launcher.ava.utilities;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {

  private ContactInfo contactInfo;

  @Before
  public void init(){
    contactInfo = new ContactInfo();
  }

  @Test
  public void ContactInfoTest(){
    assertNull(contactInfo.displayName);
    assertNull(contactInfo.number);
    assertNull(contactInfo.skypeVoiceId);
    assertNull(contactInfo.viberVoiceId);
    assertNull(contactInfo.whatsappVoiceId);

  }

}