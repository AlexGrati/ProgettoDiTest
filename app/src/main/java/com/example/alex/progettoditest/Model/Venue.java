package com.example.alex.progettoditest.Model;

/**
 * Created by Alex on 02/01/2018.
 */

public class Venue {
    private String name;
    private String city;
    private String country;
    private long latitude;
    private long longitude;

    public Venue(){
        this.name = null;
        this.city = null;
        this.city = null;
        this.latitude = 0;
        this.longitude = 0;
    }

    public Venue(String name, String city, String country, long latitude, long longitude) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
