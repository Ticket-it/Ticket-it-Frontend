package me.jdvp.androidaspectexample.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import me.jdvp.androidaspectexample.R;

public class AdminEventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_details);
        go_back = findViewById(R.id.admin_back_arrow2);
        go_back.setOnClickListener(view -> {
            startActivity(new Intent(AdminEventDetailsActivity.this, AdminEventsActivity.class));
        });
    }
}