package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import me.jdvp.androidaspectexample.R;

public class AgentEventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_event_details);
        go_back = findViewById(R.id.back_arrow3);
        go_back.setOnClickListener(view -> {
            startActivity(new Intent(AgentEventDetailsActivity.this, AgentEventsActivity.class));
        });
    }
}