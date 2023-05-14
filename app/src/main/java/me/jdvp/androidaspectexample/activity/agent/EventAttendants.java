package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.AgentEventAdapter;
import me.jdvp.androidaspectexample.Adapters.AttendantsAdapter;
import me.jdvp.androidaspectexample.Models.AttendantModel;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;

public class EventAttendants extends AppCompatActivity {
    RecyclerView attendantRecyclerView;
    AttendantsAdapter attendantsAdapter;
    ArrayList<AttendantModel> attendants = new ArrayList<>();
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendants);

        attendants.add(new AttendantModel("ahmed"));
        attendants.add(new AttendantModel("omar"));
        attendants.add(new AttendantModel("etch"));
        attendants.add(new AttendantModel("Mazen"));
        attendants.add(new AttendantModel("adham"));
        attendants.add(new AttendantModel("etch"));
        attendants.add(new AttendantModel("Mazen"));
        attendants.add(new AttendantModel("adham"));
        attendants.add(new AttendantModel("etch"));
        attendants.add(new AttendantModel("Mazen"));
        attendants.add(new AttendantModel("adham"));

        attendantRecyclerView = findViewById(R.id.attendant_recyclerView);
        attendantsAdapter = new AttendantsAdapter(this, attendants);
        attendantRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        attendantRecyclerView.setAdapter(attendantsAdapter);
    }
}