package com.example.alex.progettoditest.Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 02/01/2018.
 */

public class Artist {
    private int id;
    private String name;
    private String imgUrl;

    public Artist(){
        this.id = -1;
        this.name = null;
        this.imgUrl = null;
    }

    public Artist(int id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String img) {
        this.imgUrl = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
