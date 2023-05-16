package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.R;

public class TicketDetailsAgent extends AppCompatActivity {
    TextView event_title, event_address, event_price, date_text, time_text, location_text, attName, attPhone, attEmail, ticketID;
    Button statusBtn,confirm_attendance;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details_agent);

        statusBtn = findViewById(R.id.statusBtn);
        confirm_attendance = findViewById(R.id.confirm_attendance);

        event_title = findViewById(R.id.event_title);
        event_address = findViewById(R.id.event_address);
        event_price = findViewById(R.id.event_price);
        date_text = findViewById(R.id.date_text);
        time_text = findViewById(R.id.time_text);
        location_text = findViewById(R.id.location_text);
        attName = findViewById(R.id.attName);
        attPhone = findViewById(R.id.attPhone);
        attEmail = findViewById(R.id.attEmail);
        ticketID = findViewById(R.id.ticketID);

        Intent intent = getIntent();
        ticketID.setText(intent.getStringExtra("ticketId"));
        statusBtn.setText(intent.getStringExtra("status"));
        attName.setText(intent.getStringExtra("name"));
        attEmail.setText(intent.getStringExtra("email"));
        attPhone.setText(intent.getStringExtra("mobileNo"));
        event_title.setText(intent.getStringExtra("title"));
        event_address.setText(intent.getStringExtra("location"));
        event_price.setText(intent.getStringExtra("price"));
        date_text.setText(intent.getStringExtra("date"));
        time_text.setText(intent.getStringExtra("time"));
        location_text.setText(intent.getStringExtra("city") + ", " + intent.getStringExtra("country"));

        confirm_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}