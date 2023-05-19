package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import me.jdvp.androidaspectexample.APIModel.events.EventTypeResponse;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.user.EventsActivity;

public class EventTypeAdapter extends RecyclerView.Adapter<EventTypeAdapter.MyHolder> {
    private Context myContext ;
    private List<EventTypeResponse> myData;

    public EventTypeAdapter(Context myContext, List<EventTypeResponse> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public EventTypeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_type_card,viewGroup,false);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventTypeAdapter.MyHolder myHolder, int position) {
        myHolder.event_type_title.setText(myData.get(myHolder.getAdapterPosition()).getEventTypeName());
        myHolder.event_type_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, EventsActivity.class);
                intent.putExtra("event_ID", myData.get(myHolder.getAdapterPosition()).getEventTypeId());
                intent.putExtra("eventTypeName", myData.get(myHolder.getAdapterPosition()).getEventTypeName());

                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView event_type_image ;
        RelativeLayout event_type_card;
        TextView event_type_title;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            event_type_card = itemView.findViewById(R.id.card_view_id);
            event_type_title = itemView.findViewById(R.id.eventType_title);
            event_type_image = itemView.findViewById(R.id.eventType_img);
        }
    }
}
