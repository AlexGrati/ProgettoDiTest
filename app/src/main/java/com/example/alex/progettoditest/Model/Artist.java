package com.example.alex.progettoditest.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 02/01/2018.
 */

public class Artist {
    private int id;
    private String name;
    private List<Event> eventList;

    public Artist(){
        this.eventList = new ArrayList<>();
        this.id = -1;
        this.name = null;
    }

    public Artist(int id, String name, List<Event> eventList) {
        this.eventList = eventList;
        this.id = id;
        this.name = name;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
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
