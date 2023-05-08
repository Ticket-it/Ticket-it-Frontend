package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.AgentEventAdapter;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;

public class AgentEventsActivity extends AppCompatActivity {

    RecyclerView agentEventsRecyclerView;
    AgentEventAdapter agentEventAdapter;
    ArrayList<EventModel> events = new ArrayList<>();
    ImageView back_btn;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_events);

        Intent intent = getIntent();
        events = (ArrayList<EventModel>) intent.getSerializableExtra("events");
        back_btn = findViewById(R.id.back_arrow);
        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(AgentEventsActivity.this, AgentHome.class));
        });
        agentEventsRecyclerView = findViewById(R.id.agent_events_recycler_view);
        agentEventAdapter = new AgentEventAdapter(this, events);
        agentEventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        agentEventsRecyclerView.setAdapter(agentEventAdapter);
    }
}