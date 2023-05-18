package me.jdvp.androidaspectexample.activity.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.admin.AdminEventsActivity;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgentEventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    Button buttonViewAtt;
    TextView num_tickets_tv,eventTitle, eventAddress, eventPrice, eventLocation, eventTime, eventDate, eventDescription;
    String eventID, title, image, location, date, time, description, country, city, eventId;
    Double price;
    int num_tickets;
    SharedPreferences sharedPreferences;
    ImageView event_img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_event_details);

        Intent intent = getIntent();

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        eventTitle = findViewById(R.id.event_title);
        eventAddress = findViewById(R.id.event_address);
        eventPrice = findViewById(R.id.event_price);
        eventLocation = findViewById(R.id.location_text);
        eventTime = findViewById(R.id.time_text);
        eventDate = findViewById(R.id.date_text);
        eventDescription = findViewById(R.id.event_description);
        eventPrice = findViewById(R.id.event_price);
        buttonViewAtt = findViewById(R.id.buttonViewAtt);
        num_tickets_tv=findViewById(R.id.num_tickets);

        title = intent.getStringExtra("title");
        price = intent.getDoubleExtra("price", 0.0);
        image = intent.getStringExtra("image");
        location = intent.getStringExtra("location");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        description = intent.getStringExtra("description");
        country = intent.getStringExtra("country");
        city = intent.getStringExtra("city");
        eventID = intent.getStringExtra("eventID");
        num_tickets= intent.getIntExtra("num_tickets",0);


        event_img = findViewById(R.id.image_event_details);
        Picasso.get().load(image).into(event_img);
        num_tickets_tv.setText(String.valueOf(num_tickets)+" Tickets");
        eventTitle.setText(title);
        eventAddress.setText(location);
        eventLocation.setText(city + ", " + country);
        eventTime.setText(time);
        eventDate.setText(date);
        eventDescription.setText(description);
        eventPrice.setText(price.toString());

        buttonViewAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AgentEventDetailsActivity.this, EventAttendants.class);
                intent1.putExtra("eventId", eventID);
                intent1.putExtra("event_img", image);

                startActivity(intent1);

            }
        });

        go_back = findViewById(R.id.back_arrow3);
        go_back.setOnClickListener(view -> {
            startActivity(new Intent(AgentEventDetailsActivity.this, AgentEventsActivity.class));
        });


    }
}
