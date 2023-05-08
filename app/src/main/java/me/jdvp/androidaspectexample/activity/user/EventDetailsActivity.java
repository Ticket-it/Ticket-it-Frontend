package me.jdvp.androidaspectexample.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import me.jdvp.androidaspectexample.R;

public class EventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        go_back = findViewById(R.id.back_arrow2);
        go_back.setOnClickListener(view -> {
            startActivity(new Intent(EventDetailsActivity.this, EventsActivity.class));
        });
    }
}