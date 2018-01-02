package com.example.alex.progettoditest.Utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Alex on 02/01/2018.
 */

public class BandsintownRestRequests {
    public static final String BASE_URL = "http://api.bandsintown.com";
    private static final String BASE_URL_INFO = ".json?api_version=2.0&app_id=ProgettoDiTest";
    private static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    private BandsintownRestRequests(){}

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        asyncHttpClient.get(BASE_URL + url + BASE_URL_INFO, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        asyncHttpClient.post(BASE_URL+url+BASE_URL_INFO, params, responseHandler);
    }

}
