package com.example.alex.progettoditest.Utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alex.progettoditest.Model.Artist;

import java.util.Date;

/**
 * Created by Alex on 02/01/2018.
 */


public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "EventPlaner.db";

    public static final String ARTIST_TABLE_NAME = "artist";
    public static final String ARTIST_COLUMN_NAME = "name";
    public static final String ARTIST_COLUMN_ID = "id";
    public static final String ARTIST_COLUMN_IMG_URL = "img_url";


    public static final String EVENT_TABLE_NAME = "event";
    public static final String EVENT_COLUMN_ID = "id";
    public static final String EVENT_COLUMN_ARTIST_ID = "artist_id";
    public static final String EVENT_COLUMN_VENUE_ID = "venue_id";
    public static final String EVENT_COLUMN_TITLE = "title";
    public static final String EVENT_COLUMN_DATE = "date";

    public static final String ARTIST_EVENT_TABLE_NAME = "artist_event";
    public static final String ARTIST_EVENT_COLUMN_ID = "id";
    public static final String ARTIST_EVENT_COLUMN_ARTIST_ID = "artist_id";
    public static final String ARTIST_EVENT_COLUMN_EVENT_ID = "event_id";


    public static final String VENUE_TABLE_NAME = "venue";
    public static final String VENUE_COLUMN_ID = "id";
    public static final String VENUE_COLUMN_NAME = "name";
    public static final String VENUE_COLUMN_CITY = "city";
    public static final String VENUE_COLUMN_COUNTRY = "country";
    public static final String VENUE_COLUMN_LATITUDE = "latitude";
    public static final String VENUE_COLUMN_LONGITUDE = "longitude";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + ARTIST_TABLE_NAME +
                            "(" + ARTIST_COLUMN_ID + " integer primary key,"
                                + ARTIST_COLUMN_NAME + " text,"
                                + ARTIST_COLUMN_IMG_URL + " text)"
        );
        db.execSQL(
                "create table " + EVENT_TABLE_NAME +
                            "(" + EVENT_COLUMN_ID + " integer primary key,"
                                + EVENT_COLUMN_TITLE +" text,"
                                + EVENT_COLUMN_ARTIST_ID +" integer,"
                                + EVENT_COLUMN_VENUE_ID+" integer,"
                                + EVENT_COLUMN_DATE +" text,"
                                + "foreign key (" + EVENT_COLUMN_ARTIST_ID+") references "+ ARTIST_TABLE_NAME + "(" + ARTIST_COLUMN_ID + "),"
                                + "foreign key (" + EVENT_COLUMN_VENUE_ID+") references "+ VENUE_TABLE_NAME + "(" + VENUE_COLUMN_ID + "))"
        );

        db.execSQL(
                "create table " + ARTIST_EVENT_TABLE_NAME +
                            "(" + ARTIST_EVENT_COLUMN_ID + " integer primary key autoincrement,"
                                + ARTIST_EVENT_COLUMN_ARTIST_ID +" integer,"
                                + ARTIST_EVENT_COLUMN_EVENT_ID +" integer,"
                                + "foreign key (" + ARTIST_EVENT_COLUMN_ARTIST_ID+") references "+ ARTIST_TABLE_NAME + "(" + ARTIST_COLUMN_ID + "),"
                                + "foreign key (" + ARTIST_EVENT_COLUMN_EVENT_ID+") references "+ EVENT_TABLE_NAME + "(" + EVENT_COLUMN_ID + "))"
        );

        db.execSQL(
                "create table " + VENUE_TABLE_NAME +
                            "(" + VENUE_COLUMN_ID + " integer primary key autoincrement,"
                                + VENUE_COLUMN_NAME +" text,"
                                + VENUE_COLUMN_CITY +" text,"
                                + VENUE_COLUMN_COUNTRY +" text,"
                                + VENUE_COLUMN_LATITUDE +" integer,"
                                + VENUE_COLUMN_LONGITUDE +" integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + ARTIST_TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + VENUE_TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + ARTIST_EVENT_TABLE_NAME );
        onCreate(db);
    }


    public Cursor getArtistByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ARTIST_TABLE_NAME + " where "+ ARTIST_COLUMN_NAME + " = " + name , null );
        return res;
    }

    public boolean insertVenue(String name, String city, String country, double latitude, double longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VENUE_COLUMN_NAME, name);
        contentValues.put(VENUE_COLUMN_CITY, city);
        contentValues.put(VENUE_COLUMN_COUNTRY, country);
        contentValues.put(VENUE_COLUMN_LATITUDE, latitude);
        contentValues.put(VENUE_COLUMN_LONGITUDE, longitude);
        db.insert(VENUE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertArtist(int id, String name, String imgUrl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTIST_COLUMN_ID,id);
        contentValues.put(ARTIST_COLUMN_NAME, name);
        contentValues.put(ARTIST_COLUMN_IMG_URL, imgUrl);
        db.insert(ARTIST_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor containsArtist(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from " + ARTIST_TABLE_NAME + " where " + ARTIST_COLUMN_ID + " = " + id+"", null);
        return res;
    }

    public Cursor containsEvent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from " + EVENT_TABLE_NAME + " where "+ EVENT_COLUMN_ID +" = " + id+"", null);
        return res;
    }

    public Cursor containsVenue(String name, double latitude, double longitude){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from " + VENUE_TABLE_NAME +
                                                " where "+ VENUE_COLUMN_LATITUDE +" = " + latitude +
                                                " and " + VENUE_COLUMN_LONGITUDE + " = " + longitude, null);
        return res;
    }

    public boolean insertArtistEvent(int artistId, int eventId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTIST_EVENT_COLUMN_ARTIST_ID, artistId);
        contentValues.put(ARTIST_EVENT_COLUMN_EVENT_ID, eventId);
        db.insert(ARTIST_EVENT_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertEvent(int id, int artistId, String title, int venueId, String date ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_ID, id);
        contentValues.put(EVENT_COLUMN_ARTIST_ID, artistId);
        contentValues.put(EVENT_COLUMN_TITLE, title);
        contentValues.put(EVENT_COLUMN_VENUE_ID, venueId);
        contentValues.put(EVENT_COLUMN_DATE, date);
        db.insert(EVENT_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getVenueById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from " + VENUE_TABLE_NAME + " where "+ VENUE_COLUMN_ID +" = " + id+"", null);
        return res;
    }
}
