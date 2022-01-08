package com.example.eventmanagment;

import android.app.Application;

import java.util.ArrayList;

public class globalclass extends Application {
   public ArrayList<CatteringItem> catererlist = new ArrayList<>();



    public  String lat;
    public  String lon;

    public String getLon() {
        return lon;
    }
    public String getLat() {
        return lat;
    }



}
