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

import java.util.List;

import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.user.EventDetailsActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> {
    private Context myContext ;
    private List<EventResponse> myData;

    public EventAdapter(Context myContext, List<EventResponse> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public EventAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_card,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyHolder myHolder, int position) {
        myHolder.image.setImageResource(R.drawable.image_event);
        myHolder.title.setText(myData.get(myHolder.getAdapterPosition()).getEventName());
        myHolder.address.setText(myData.get(myHolder.getAdapterPosition()).getLocation());
        myHolder.price.setText(String.valueOf(myData.get(myHolder.getAdapterPosition()).getPrice()));

        myHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, EventDetailsActivity.class);
                intent.putExtra("title", myData.get(myHolder.getAdapterPosition()).getEventName());
                intent.putExtra("country", myData.get(myHolder.getAdapterPosition()).getCountry());
                intent.putExtra("city", myData.get(myHolder.getAdapterPosition()).getCity());
                intent.putExtra("price", myData.get(myHolder.getAdapterPosition()).getPrice());
                intent.putExtra("image", myData.get(myHolder.getAdapterPosition()).getImageURL());
                intent.putExtra("date", myData.get(myHolder.getAdapterPosition()).getDate());
                intent.putExtra("time", myData.get(myHolder.getAdapterPosition()).getTime());
                intent.putExtra("location", myData.get(myHolder.getAdapterPosition()).getLocation());
                intent.putExtra("description", myData.get(myHolder.getAdapterPosition()).getDescription());
                intent.putExtra("eventTypeId", myData.get(myHolder.getAdapterPosition()).getEventTypeId());
                intent.putExtra("eventID", myData.get(myHolder.getAdapterPosition()).getEventId());

                intent.putExtra("eventObj", myData.get(myHolder.getAdapterPosition()));



                myContext.startActivity(intent);
            }
        });
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