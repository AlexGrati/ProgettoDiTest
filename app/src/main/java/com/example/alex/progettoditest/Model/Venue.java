package com.example.alex.progettoditest.Model;

/**
 * Created by Alex on 02/01/2018.
 */

public class Venue {

    private int id;
    private String name;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    public Venue(){
        this.id = -1;
        this.name = null;
        this.city = null;
        this.city = null;
        this.latitude = 0;
        this.longitude = 0;
    }

    public Venue(int id, String name, String city, String country, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
