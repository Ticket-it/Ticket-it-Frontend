package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.AgentEventsActivity;

public class AgentEventTypeAdapter extends RecyclerView.Adapter<AgentEventTypeAdapter.MyHolder> {
    private Context myContext ;
    private ArrayList<EventTypeModel> myData;

    public AgentEventTypeAdapter(Context myContext, ArrayList<EventTypeModel> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public AgentEventTypeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_type_card,viewGroup,false);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentEventTypeAdapter.MyHolder myHolder, int position) {
        //Picasso.get().load(myData.get(myHolder.getAdapterPosition()).getImage()).into(myHolder.event_type_image);
        myHolder.event_type_image.setImageResource(R.drawable.event_type_img);
        myHolder.event_type_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, AgentEventsActivity.class);
                intent.putExtra("events", (Serializable) myData.get(myHolder.getAdapterPosition()).getEvents());
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
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            event_type_card = itemView.findViewById(R.id.card_view_id);
        }
    }
}
