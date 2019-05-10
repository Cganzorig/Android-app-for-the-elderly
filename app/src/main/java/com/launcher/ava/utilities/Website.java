package com.launcher.ava.utilities;

public class Website {

  public Integer logoId = 0;
  public String name = null;
  public String url = null;


  public Website() { }

  public Website(String name, String url) {
    this.name= name;
    this.url=url;
  }

  public Website(int logoId, String name, String url) {
    this.logoId = logoId;
    this.name= name;
    this.url=url;
  }

}
