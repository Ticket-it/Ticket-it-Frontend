package me.jdvp.androidaspectexample.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.AdminEventAdapter;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;

public class AdminEventsActivity extends AppCompatActivity {

    RecyclerView adminEventsRecyclerView;
    AdminEventAdapter adminEventAdapter;
    ArrayList<EventModel> events = new ArrayList<>();
    ImageView back_btn;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_events);
        Intent intent = getIntent();
        events = (ArrayList<EventModel>) intent.getSerializableExtra("events");
        back_btn = findViewById(R.id.admin_back_arrow);
        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(AdminEventsActivity.this, AdminHome.class));
        });
        adminEventsRecyclerView = findViewById(R.id.admin_events_recycler_view);
        adminEventAdapter = new AdminEventAdapter(this, events);
        adminEventsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adminEventsRecyclerView.setAdapter(adminEventAdapter);
    }
}