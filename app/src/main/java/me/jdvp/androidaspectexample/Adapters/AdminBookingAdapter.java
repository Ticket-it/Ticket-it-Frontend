package me.jdvp.androidaspectexample.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.admin.ApproveAllResponse;
import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.admin.TicketDetailsAdmin;
import me.jdvp.androidaspectexample.activity.agent.AgentEventDetailsActivity;
import me.jdvp.androidaspectexample.activity.agent.TicketDetailsAgent;

public class AdminBookingAdapter extends RecyclerView.Adapter<AdminBookingAdapter.MyHolder> {

    private Context myContext ;
    private ArrayList<AttendanceResponse> myData;
    String eventTypeName;

    public AdminBookingAdapter(Context myContext, ArrayList<AttendanceResponse> myData, String eventTypeName) {
        this.myContext = myContext;
        this.myData = myData;
        this.eventTypeName=eventTypeName;
    }

    @NonNull
    @Override
    public AdminBookingAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.attendant_item,viewGroup,false);
        return new AdminBookingAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBookingAdapter.MyHolder myHolder, int position) {
        myHolder.name.setText(myData.get(myHolder.getAdapterPosition()).getUserName());
        myHolder.number.setText("Attendant #"+(position+1));
        Log.d("price",String.valueOf(myData.get(myHolder.getAdapterPosition()).getEventDetails().getPrice()));

        myHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, TicketDetailsAdmin.class);
                intent.putExtra("title", myData.get(myHolder.getAdapterPosition()).getEventDetails().getEventName());
                intent.putExtra("country", myData.get(myHolder.getAdapterPosition()).getEventDetails().getCountry());
                intent.putExtra("city", myData.get(myHolder.getAdapterPosition()).getEventDetails().getCity());
                intent.putExtra("price", String.valueOf(myData.get(myHolder.getAdapterPosition()).getEventDetails().getPrice()));
                intent.putExtra("date", myData.get(myHolder.getAdapterPosition()).getEventDetails().getDate());
                intent.putExtra("time", myData.get(myHolder.getAdapterPosition()).getEventDetails().getTime());
                intent.putExtra("location", myData.get(myHolder.getAdapterPosition()).getEventDetails().getLocation());
                intent.putExtra("eventID", myData.get(myHolder.getAdapterPosition()).getEventId());
                intent.putExtra("ticketId", myData.get(myHolder.getAdapterPosition()).getTicketId());
                intent.putExtra("email", myData.get(myHolder.getAdapterPosition()).getEmail());
                intent.putExtra("mobileNo", myData.get(myHolder.getAdapterPosition()).getMobileNo());
                intent.putExtra("status", myData.get(myHolder.getAdapterPosition()).getStatus());
                intent.putExtra("name", myData.get(myHolder.getAdapterPosition()).getUserName());
                intent.putExtra("userId", myData.get(myHolder.getAdapterPosition()).getUserId());
                intent.putExtra("eventTypeId", myData.get(myHolder.getAdapterPosition()).getEventDetails().getEventTypeId());
                intent.putExtra("eventTypeName", eventTypeName);

                myContext.startActivity(intent);
                ((Activity) myContext).finish();

            }
        });
    }

    public void updateData(List<ApproveAllResponse> newData) {
        // Clear the existing data
        myData.clear();

        // Add the new data
        myData.addAll(myData);

        // Notify the adapter of the data change
        notifyDataSetChanged();
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

        TextView name;
        TextView number;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.attendant_name);
            number = itemView.findViewById(R.id.attendant_num);

        }
    }
}
