package me.jdvp.androidaspectexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Models.AttendantModel;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;

public class AttendantsAdapter extends RecyclerView.Adapter<AttendantsAdapter.MyHolder> {

    private Context myContext ;
    private ArrayList<AttendantModel> myData= new ArrayList<>();

    public AttendantsAdapter(Context myContext, ArrayList<AttendantModel> myData) {
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

        myHolder.name.setText(myData.get(myHolder.getAdapterPosition()).getName());
        myHolder.number.setText("Attendant #"+(position+1));
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
