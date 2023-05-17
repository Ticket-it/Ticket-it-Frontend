package me.jdvp.androidaspectexample.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.AgentEventDetailsActivity;
import me.jdvp.androidaspectexample.activity.agent.AgentEventsActivity;
import me.jdvp.androidaspectexample.activity.agent.EventAttendants;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminEventDetailsActivity extends AppCompatActivity {
    ImageView go_back, delete_image;
    TextView eventTitle, eventAddress, eventPrice, eventLocation, eventTime, eventDate, eventDescription;
    String eventID, title, image, location, date, time, description, country, city, eventId;
    Double price;
    SharedPreferences sharedPreferences;
    Retrofit retrofit;
    AdminService adminService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_details);

        Intent intent = getIntent();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit.create(AdminService.class);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        eventTitle = findViewById(R.id.event_title);
        eventAddress = findViewById(R.id.event_address);
        eventPrice = findViewById(R.id.event_price);
        eventLocation = findViewById(R.id.location_text);
        eventTime = findViewById(R.id.time_text);
        eventDate = findViewById(R.id.date_text);
        eventDescription = findViewById(R.id.event_description);
        eventPrice = findViewById(R.id.event_price);
        delete_image = findViewById(R.id.delete_image);

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

        eventTitle.setText(title);
        eventAddress.setText(location);
        eventLocation.setText(city + ", " + country);
        eventTime.setText(time);
        eventDate.setText(date);
        eventDescription.setText(description);
        eventPrice.setText(price.toString());

        go_back = findViewById(R.id.back_arrow3);
        go_back.setOnClickListener(view -> {
            finish();
        });

        Log.e("eventId", Objects.requireNonNullElse(eventId, "null value"));

        delete_image.setOnClickListener(view -> {

            var call = adminService.deleteEvent(eventId);
            call.enqueue(new Callback<ConfirmationResponse>() {
                @Override
                public void onResponse(@NonNull Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {
                    if (response.isSuccessful()) {
                        var responseData = response.body();
                        assert responseData != null;
                        Toast.makeText(AdminEventDetailsActivity.this, responseData.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        if (response.errorBody() != null) {
                            try {
                                String errorResponse = response.errorBody().string();
                                ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
                                String errorMessage = error.getMessage();
                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
                    Toast.makeText(AdminEventDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}