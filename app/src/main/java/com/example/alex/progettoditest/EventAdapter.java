package com.example.alex.progettoditest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.progettoditest.Model.Event;
import com.example.alex.progettoditest.Model.Venue;
import com.example.alex.progettoditest.Utils.DBHelper;

import java.util.List;

/**
 * Created by Alex on 02/01/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CardHolder> {
    public static class CardHolder extends RecyclerView.ViewHolder {
        private Context context;
        private CardView cardView;
        private TextView textViewLocation;
        private TextView textViewEventName;

        public CardHolder(View v, final Context context){
            super(v);
            cardView = v.findViewById(R.id.cardViewId);
            textViewLocation = v.findViewById(R.id.textViewLocation);
            textViewEventName = v.findViewById(R.id.textViewEventName);
            this.context = context;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    private Context context;
    private List<Event> eventList;
    private Event e;

    public EventAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent,false);
        CardHolder cardHolder = new CardHolder(v,context);
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
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
