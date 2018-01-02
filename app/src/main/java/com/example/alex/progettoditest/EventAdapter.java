package com.example.alex.progettoditest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.SystemClock;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.alex.progettoditest.Model.Event;
import com.example.alex.progettoditest.Model.Venue;
import com.example.alex.progettoditest.Utils.DBHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Alex on 02/01/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CardHolder> {
    public static class CardHolder extends RecyclerView.ViewHolder {
        private Context context;
        private CardView cardView;
        private TextView textViewLocation;
        private TextView textViewEventName;
        private CheckBox checkBox;


        public CardHolder(View v, final Context context, final Event event){
            super(v);
            cardView = v.findViewById(R.id.cardViewId);
            textViewLocation = v.findViewById(R.id.textViewLocation);
            textViewEventName = v.findViewById(R.id.textViewEventName);
            checkBox = v.findViewById(R.id.checkBox);

            //this.setIsRecyclable(false);
            this.context = context;
            /*
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });*/
        }
    }

    private Context context;
    private List<Event> eventList;
    private Event e;
    private PendingIntent pendingIntent;
    private SharedPreferences sharedPreferences;

    public EventAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent,false);
        CardHolder cardHolder = new CardHolder(v,context,e);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        e = eventList.get(position);
        holder.textViewEventName.setText(e.getTitle());
        DBHelper dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.getVenueById(e.getVenueId());
        cursor.moveToFirst();
        String location = cursor.getString(1) +", " + cursor.getString(2);
        holder.textViewLocation.setText(location);

        //holder.setIsRecyclable(false);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = (CheckBox) compoundButton;

                if(checkBox.isChecked()) {
                    Calendar calendar = Calendar.getInstance();
                    //Date date = event.getDateTime();
                    //calendar.set(Calendar.DATE, date.getDate());
                    calendar.set(Calendar.HOUR_OF_DAY, 8);
                    calendar.set(Calendar.MINUTE, 25);
                    calendar.set(Calendar.AM_PM, Calendar.PM);

                    Intent myIntent = new Intent(context, MainActivity.class);
                    pendingIntent = PendingIntent.getBroadcast(context, 0 , myIntent, 0);

                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60000, pendingIntent);


                        /*
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        Gson gson = new Gson();
                        String json = sharedPreferences.getString("PENDING_INTENT_LIST", "");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Type type = new TypeToken<List<PendingIntent>>() {}.getType();

                        List<PendingIntent> pendingIntentList = gson.fromJson(json, type);
                        if (pendingIntentList == null) {*/
                    List<PendingIntent> pendingIntentList = new ArrayList<>();
                    pendingIntentList.add(pendingIntent);
                        /*}

                        pendingIntentList.add(pendingIntent);
                        String jsString = gson.toJson(pendingIntentList);
                        editor.putString("PENDING_INTENT_LIST",jsString);
                        editor.apply();*/
                }
            }
        });
        cursor.close();
        dbHelper.close();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
