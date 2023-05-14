package me.jdvp.androidaspectexample.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import me.jdvp.androidaspectexample.R;

public class EventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    TextView eventTitle, eventAddress, eventPrice, eventLocation, eventTime, eventDate, eventDescription;
    String title, address, image, location, date, time, description, country, city;
    Double price;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        eventTitle = findViewById(R.id.event_title);
        eventAddress = findViewById(R.id.event_address);
        eventPrice = findViewById(R.id.event_price);
        eventLocation = findViewById(R.id.location_text);
        eventTime = findViewById(R.id.time_text);
        eventDate = findViewById(R.id.date_text);
        eventDescription = findViewById(R.id.event_description);
        eventPrice = findViewById(R.id.event_price);

        title = intent.getStringExtra("title");
        price = intent.getDoubleExtra("price", 0.0);
        image = intent.getStringExtra("image");
        location = intent.getStringExtra("location");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        description = intent.getStringExtra("description");
        country = intent.getStringExtra("country");
        city = intent.getStringExtra("city");


        eventTitle.setText(title);
        eventAddress.setText(location);
        eventLocation.setText(city + ", " + country);
        eventTime.setText(time);
        eventDate.setText(date);
        eventDescription.setText(description);
        eventPrice.setText(price.toString());


        go_back = findViewById(R.id.back_arrow2);
        go_back.setOnClickListener(view -> {
            startActivity(new Intent(EventDetailsActivity.this, EventsActivity.class));
        });

    }
}