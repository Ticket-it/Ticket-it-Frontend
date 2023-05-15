package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.jdvp.androidaspectexample.APIModel.events.TicketsHistoryResponse;
import me.jdvp.androidaspectexample.R;

public class EventHistoryAdapter extends RecyclerView.Adapter<EventHistoryAdapter.MyHolder> {
    private Context myContext ;
    private List<TicketsHistoryResponse> myData;

    public EventHistoryAdapter(Context myContext, List<TicketsHistoryResponse> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public EventHistoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.event_history_card,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHistoryAdapter.MyHolder myHolder, int position) {
        myHolder.image.setImageResource(R.drawable.image_event);
        myHolder.title.setText(myData.get(myHolder.getAdapterPosition()).getEventDetails().getEventName());
        myHolder.address.setText(myData.get(myHolder.getAdapterPosition()).getEventDetails().getLocation());
        myHolder.price.setText(String.valueOf(myData.get(myHolder.getAdapterPosition()).getEventDetails().getPrice()));
        myHolder.status.setText(myData.get(myHolder.getAdapterPosition()).getStatus());
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

        TextView status;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            address = itemView.findViewById(R.id.event_address);
            price = itemView.findViewById(R.id.event_price);
            image = itemView.findViewById(R.id.event_image);
            status = itemView.findViewById(R.id.event_status);

        }
    }
}
