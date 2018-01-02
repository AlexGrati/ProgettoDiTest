package com.example.alex.progettoditest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.alex.progettoditest.Utils.ResponseController;

public class MainActivity extends AppCompatActivity implements ResponseController{
    private ResponseController responseController;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseController = this;
        progressDialog = new ProgressDialog(MainActivity.this);
    }

    public void onSearchButtonPressed(View v){

    }

    @Override
    public void respondOnRecevedData(){
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }
}
