package me.jdvp.androidaspectexample.activity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.EventAdapter;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;

public class EventsActivity extends AppCompatActivity {

    RecyclerView eventsRecyclerView;
    EventAdapter eventAdapter;
    ArrayList<EventModel> events = new ArrayList<>();
    ImageView back_btn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Intent intent = getIntent();
        events = (ArrayList<EventModel>) intent.getSerializableExtra("events");
        back_btn = findViewById(R.id.back_arrow);
        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(EventsActivity.this, HomeActivity.class));
        });
        eventsRecyclerView = findViewById(R.id.events_recycler_view);
        eventAdapter = new EventAdapter(this, events);
        eventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        eventsRecyclerView.setAdapter(eventAdapter);
    }
}