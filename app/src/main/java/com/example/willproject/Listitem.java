package com.example.willproject;

import android.media.Image;

public class Listitem {
    private String head;
    private  String des;
    private String imageUrl;

    public Listitem(String head, String des,String imageUrl) {
        this.head = head;
        this.des = des;
        this.imageUrl = imageUrl;

    }

    public String getHead() {
        return head;
    }

    public String getDes() {
        return des;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
