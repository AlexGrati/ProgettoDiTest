package com.example.alex.progettoditest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.progettoditest.Model.Artist;
import com.example.alex.progettoditest.Model.Event;
import com.example.alex.progettoditest.Utils.BandsintownRestRequests;
import com.example.alex.progettoditest.Utils.JSONParser;
import com.example.alex.progettoditest.Utils.ResponseController;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity implements ResponseController{
    private EditText search;
    private TextView textView;
    private ResponseController responseController;
    private ProgressDialog progressDialog;
    private String searchElements;
    private Artist artist;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseController = this;
        progressDialog = new ProgressDialog(MainActivity.this);
        search = findViewById(R.id.editTextSearch);
        textView = findViewById(R.id.textView);
    }

    public void onSearchButtonPressed(View v){
        searchElements = search.getText().toString();
        if(!searchElements.equals("")){
            if(isNetworkAvailable()) {
                searchArtist();
            }else {
                Toast.makeText(getApplicationContext(), "Please Connect to internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void respondOnRecevedData(){
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public void searchArtist(){
        progressDialog.setTitle("Searching data...");
        progressDialog.show();

        BandsintownRestRequests.get("/artists/"+searchElements, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    artist = JSONParser.getArtist(getApplicationContext(), new String(responseBody));
                    searchEvents();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(statusCode >= 400 && statusCode < 500){
                    Log.e("ERROR", new String(responseBody));
                    Toast.makeText(getApplicationContext(), "Server Unavailable", Toast.LENGTH_SHORT).show();
                }
                responseController.respondOnRecevedData();
            }
        });
    }

    public void searchEvents(){
        BandsintownRestRequests.get("/artists/" + artist.getName() + "/events", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    List<Event> eventList = JSONParser.getEvents(getApplicationContext(), new String(responseBody));
                    recyclerView = findViewById(R.id.recyclerViewId);
                    linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);

                    eventAdapter = new EventAdapter(getApplicationContext(),  eventList);
                    recyclerView.setAdapter(eventAdapter);
                    textView.setVisibility(View.VISIBLE);

                }
                responseController.respondOnRecevedData();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(statusCode >= 400 && statusCode < 500){
                    Log.e("ERROR", new String(responseBody));
                    Toast.makeText(getApplicationContext(), "Server Unavailable", Toast.LENGTH_SHORT).show();
                }
                responseController.respondOnRecevedData();
            }
        });
    }
}
