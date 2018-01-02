package com.example.alex.progettoditest.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Alex on 02/01/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive( Context context,Intent intent){
        Log.i("App", "called receiver method");
        try{
            PushNotification.sendNotification(intent, null , null, context);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
