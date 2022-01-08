package com.example.eventmanagment;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.util.ArrayList;

public class app extends Application {

    public static ArrayList<CatteringItem> catererlist = new ArrayList<>();

    public static HallDescriptionItem hallDescriptionItem = new HallDescriptionItem();
    public static HallDescriptionItem photographeritem = new HallDescriptionItem();


    public static  String lat;
    public static String lon;

    public String getLon() {
        return lon;
    }
    public String getLat() {
        return lat;
    }

    public void onCreate() {
        super.onCreate();

        lat = "0";
        lon = "0";
        hallDescriptionItem.name = "0";
        photographeritem.name = "0";


        }

        protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }














}

