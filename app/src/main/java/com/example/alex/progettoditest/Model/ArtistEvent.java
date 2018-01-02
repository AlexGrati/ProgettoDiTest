package com.example.alex.progettoditest.Model;

/**
 * Created by Alex on 02/01/2018.
 */

public class ArtistEvent {
    private int artistId;
    private int eventId;

    public ArtistEvent(int artistId, int eventId) {
        this.artistId = artistId;
        this.eventId = eventId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
