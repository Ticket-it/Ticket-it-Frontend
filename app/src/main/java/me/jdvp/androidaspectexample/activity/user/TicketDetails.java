package me.jdvp.androidaspectexample.activity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import me.jdvp.androidaspectexample.APIModel.Events.EventResponse;
import me.jdvp.androidaspectexample.R;

public class TicketDetails extends AppCompatActivity {
    String ticketNum, phone, email, status, name;
    EventResponse eventResponse;
    TextView event_title, event_address, event_price, date_text, time_text, location_text, attName, attPhone, attEmail, ticketID;
    Button statusBtn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        statusBtn = findViewById(R.id.statusBtn);
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
        eventResponse = (EventResponse) intent.getSerializableExtra("eventObj");
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("userName", "0");
        email = sharedPreferences.getString("userEmail", "0");
        phone = sharedPreferences.getString("userPhone", "0");

        status = intent.getStringExtra("ticketStatus");
        ticketNum = intent.getStringExtra("ticketID");

        ticketID.setText(ticketNum);
        statusBtn.setText(status);

        attName.setText(name);
        attEmail.setText(email);
        attPhone.setText(phone);

        event_title.setText(eventResponse.getEventName());
        event_address.setText(eventResponse.getLocation());
        event_price.setText(String.valueOf(eventResponse.getPrice()));
        date_text.setText(eventResponse.getDate());
        time_text.setText(eventResponse.getTime());
        location_text.setText(eventResponse.getCity() + ", " + eventResponse.getCountry());



    }
}