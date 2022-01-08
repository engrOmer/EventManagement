package com.example.eventmanagment;

import android.net.Uri;

import java.util.ArrayList;

public class HallDescriptionItem {
    String name;
    String price;
    String logourl;
    String phonenumber;
    String seats;
    String lat;
    String lon;
    String address;

    public HallDescriptionItem(String name, String price, String logourl, String phonenumber, String seats, String lat, String lon, String address, String firstimage, String secondimage) {
        this.name = name;
        this.price = price;
        this.logourl = logourl;
        this.phonenumber = phonenumber;
        this.seats = seats;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.firstimage = firstimage;
        this.secondimage = secondimage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    String firstimage;
    String secondimage;

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getSecondimage() {
        return secondimage;
    }

    public void setSecondimage(String secondimage) {
        this.secondimage = secondimage;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }


    public HallDescriptionItem(String name, String price, String logourl, String phonenumber, String seats, String lat, String lon, String firstimage, String secondimage) {
        this.name = name;
        this.price = price;
        this.logourl = logourl;
        this.phonenumber = phonenumber;
        this.seats = seats;
        this.lat = lat;
        this.lon = lon;
        this.firstimage = firstimage;
        this.secondimage = secondimage;
    }

    public HallDescriptionItem() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    }
