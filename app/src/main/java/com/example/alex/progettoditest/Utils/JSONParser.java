package com.example.alex.progettoditest.Utils;

import android.content.Context;
import android.database.Cursor;

import com.example.alex.progettoditest.Model.Artist;
import com.example.alex.progettoditest.Model.Event;
import com.example.alex.progettoditest.Model.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 02/01/2018.
 */

public class JSONParser {

    public static Artist getArtist(Context context, String response){
        DBHelper dbHelper =  new DBHelper(context);
        Artist artist = new Artist();
        try{
            JSONObject jsonObject = new JSONObject(response);
            Iterator iter = jsonObject.keys();
            while(iter.hasNext()){
                String key = (String) iter.next();
                String val = jsonObject.getString(key);
                switch (key){
                    case "id":
                        artist.setId(Integer.parseInt(val));
                        break;
                    case "name":
                        artist.setName(val);
                        break;
                    case "image_url":
                        artist.setImgUrl(val);
                        break;
                }
            }
            if(dbHelper.containsArtist(artist.getId()).getCount() == 0)
                dbHelper.insertArtist(artist.getId(), artist.getName(), artist.getImgUrl());
            dbHelper.close();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return artist;
    }

    public static List<Event> getEvents(Context context,String response){
        DBHelper dbHelper =  new DBHelper(context);
        List<Event> eventList = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Iterator iter = jsonObject.keys();
                Event event = new Event();
                Venue venue;
                while (iter.hasNext()){
                    String key = (String) iter.next();
                    String val = jsonObject.getString(key);
                    switch (key){
                        case "id":
                            event.setId(Integer.parseInt(val));
                            break;
                        case "artist_id":
                            event.setArtistId(Integer.parseInt(val));
                            break;
                        case "title":
                            event.setTitle(val);
                            break;
                        case "datetime":
                            val = val.replace("T", " ");
                            Date d = DateConversion.formatStringToDate(val);
                            event.setDateTime(d);
                            break;
                        case "venue":
                            venue = getVenue(context, jsonObject.getJSONObject(key));
                            event.setVenueId(venue.getId());
                    }
                }
                if(dbHelper.containsEvent(event.getId()).getCount() == 0){
                    dbHelper.insertEvent(event.getId(), event.getArtistId(), event.getTitle(), event.getVenueId(), DateConversion.formatDateToString(event.getDateTime()));
                }
                dbHelper.close();
                eventList.add(event);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return eventList;
    }

    public static Venue getVenue(Context context,JSONObject jsonObject){
        Iterator iter = jsonObject.keys();
        Venue venue = new Venue();

        while (iter.hasNext()){
            String key = (String) iter.next();
            try {
                String val = jsonObject.getString(key);
                switch (key){
                    case "name":
                        venue.setName(val);
                        break;
                    case "city":
                        venue.setCity(val);
                        break;
                    case "country":
                        venue.setCountry(val);
                        break;
                    case "latitude":
                        venue.setLatitude(Double.parseDouble(val));
                        break;
                    case "longitude":
                        venue.setLongitude(Double.parseDouble(val));
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DBHelper dbHelper =  new DBHelper(context);
        if(dbHelper.containsVenue(venue.getName(), venue.getLatitude(), venue.getLongitude()).getCount() == 0 ){
            dbHelper.insertVenue(venue.getName(), venue.getCity(), venue.getCountry(), venue.getLatitude(), venue.getLongitude());
        }
        Cursor cursor = dbHelper.containsVenue(venue.getName(), venue.getLatitude(), venue.getLongitude());
        cursor.moveToFirst();
        venue.setId(Integer.parseInt(cursor.getString(0)));
        cursor.close();
        dbHelper.close();
        return venue;
    }
}
