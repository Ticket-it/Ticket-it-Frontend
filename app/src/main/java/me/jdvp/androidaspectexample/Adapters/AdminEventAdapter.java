package me.jdvp.androidaspectexample.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.admin.AdminBookingActivity;
import me.jdvp.androidaspectexample.activity.admin.AdminEventDetailsActivity;

public class AdminEventAdapter extends RecyclerView.Adapter<AdminEventAdapter.MyHolder> {
    private Context myContext ;
    private List<EventResponse> myData;
    private String eventTypeName;

    public AdminEventAdapter(Context myContext, List<EventResponse> myData, String eventTypeName) {
        this.myContext = myContext;
        this.myData = myData;
        this.eventTypeName=eventTypeName;
    }

    @NonNull
    @Override
    public AdminEventAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_card_admin,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminEventAdapter.MyHolder myHolder, int position) {
        Picasso.get().load(myData.get(myHolder.getAdapterPosition()).getImageURL()).into(myHolder.image);
        myHolder.title.setText(myData.get(myHolder.getAdapterPosition()).getEventName());
        myHolder.address.setText(myData.get(myHolder.getAdapterPosition()).getLocation());
        myHolder.price.setText(String.valueOf(myData.get(myHolder.getAdapterPosition()).getPrice()));

        myHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, AdminEventDetailsActivity.class);
                intent.putExtra("title", myData.get(myHolder.getAdapterPosition()).getEventName());
                intent.putExtra("num_tickets", myData.get(myHolder.getAdapterPosition()).getAvailableTickets());
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
                intent.putExtra("eventTypeName", eventTypeName);

                myContext.startActivity(intent);
                ((Activity) myContext).finish();
            }
        });
        myHolder.view_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(myContext, AdminBookingActivity.class);
                intent1.putExtra("eventId", myData.get(myHolder.getAdapterPosition()).getEventId());
                intent1.putExtra("eventTypeId", myData.get(myHolder.getAdapterPosition()).getEventTypeId());
                intent1.putExtra("eventTypeName", eventTypeName);
                myContext.startActivity(intent1);
                ((Activity) myContext).finish();

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
        Button view_booking_btn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            address = itemView.findViewById(R.id.event_address);
            price = itemView.findViewById(R.id.event_price);
            image = itemView.findViewById(R.id.event_image);
            view_booking_btn = itemView.findViewById(R.id.view_booking_btn);


        }
    }
}
