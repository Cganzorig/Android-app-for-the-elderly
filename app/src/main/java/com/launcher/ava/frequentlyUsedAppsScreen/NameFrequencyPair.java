package com.launcher.ava.frequentlyUsedAppsScreen;

public class NameFrequencyPair implements Comparable<NameFrequencyPair>{

    private String pack_name;
    private Integer freq;

    public NameFrequencyPair(String s){
        pack_name = s;
        freq = 0;
    }

    public Integer getFreq(){return freq;}
    public String getPackName(){return pack_name;}
    public void incrementFreq(){freq+=1;}

    @Override
    public int compareTo(NameFrequencyPair o) {
        return (this.freq > o.getFreq()) ? 1: (this.freq==o.getFreq()) ? 0 : -1;
    }
}
