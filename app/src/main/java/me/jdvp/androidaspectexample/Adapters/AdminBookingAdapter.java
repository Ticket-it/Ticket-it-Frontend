package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.AgentEventDetailsActivity;

public class AdminBookingAdapter extends RecyclerView.Adapter<AdminBookingAdapter.MyHolder> {

    private Context myContext ;
    private ArrayList<EventModel> myData= new ArrayList<>();

    public AdminBookingAdapter(Context myContext, ArrayList<EventModel> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public AdminBookingAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_card,viewGroup,false);
        return new AdminBookingAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBookingAdapter.MyHolder myHolder, int position) {
        myHolder.image.setImageResource(R.drawable.image_event);
        myHolder.title.setText(myData.get(myHolder.getAdapterPosition()).getTitle());
        myHolder.address.setText(myData.get(myHolder.getAdapterPosition()).getAddress());
        myHolder.price.setText(String.valueOf(myData.get(myHolder.getAdapterPosition()).getPrice()));

//        myHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        try {
            return myData.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView address;
        TextView price;

        ImageView image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            address = itemView.findViewById(R.id.event_address);
            price = itemView.findViewById(R.id.event_price);
            image = itemView.findViewById(R.id.event_image);

        }
    }
}
