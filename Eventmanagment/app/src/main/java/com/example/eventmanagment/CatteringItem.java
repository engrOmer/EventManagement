package com.example.eventmanagment;

import android.os.StrictMode;

public class CatteringItem {

    String item;
    String type;
    String price;

    public CatteringItem(String item, String type, String price) {
        this.item = item;
        this.type = type;
        this.price = price;
    }


    public CatteringItem() {
       }






    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
