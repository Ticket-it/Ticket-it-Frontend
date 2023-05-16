package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.TicketDetailsAgent;

public class AttendantsAdapter extends RecyclerView.Adapter<AttendantsAdapter.MyHolder> {

    private Context myContext ;
    private ArrayList<AttendanceResponse> myData;

    public AttendantsAdapter(Context myContext, ArrayList<AttendanceResponse> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }


    @NonNull
    @Override
    public AttendantsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        view = layoutInflater.inflate(R.layout.attendant_item,viewGroup,false);
        return new AttendantsAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendantsAdapter.MyHolder myHolder, int position) {
        myHolder.name.setText(myData.get(myHolder.getAdapterPosition()).getUserName());
        myHolder.number.setText("Attendant #"+(position+1));
        Log.d("price",String.valueOf(myData.get(myHolder.getAdapterPosition()).getEventDetails().getPrice()));

        myHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, TicketDetailsAgent.class);
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

                //intent.putExtra("eventObj", myData.get(myHolder.getAdapterPosition()));
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
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
