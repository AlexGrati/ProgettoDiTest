package com.example.alex.progettoditest.Model;

import java.util.Date;

/**
 * Created by Alex on 02/01/2018.
 */

public class Event {
    private int id;
    private int artistId;
    private String title;
    private Date dateTime;
    private Venue venue;

    public Event() {
        this.id = -1;
        this.artistId = -1;
        this.title = null;
        this.dateTime = null;
        this.venue = null;
    }

    public Event(int id, int artistId, String title, Date dateTime, Venue venue) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.dateTime = dateTime;
        this.venue = venue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
