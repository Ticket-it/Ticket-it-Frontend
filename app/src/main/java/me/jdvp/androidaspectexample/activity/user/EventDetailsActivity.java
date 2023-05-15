package me.jdvp.androidaspectexample.activity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

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

import java.util.List;

import me.jdvp.androidaspectexample.APIModel.Events.BookEventResponse;
import me.jdvp.androidaspectexample.APIModel.Events.EventResponse;
import me.jdvp.androidaspectexample.Adapters.EventAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    Button buttonBook;
    TextView eventTitle, eventAddress, eventPrice, eventLocation, eventTime, eventDate, eventDescription;
    String eventID, title, image, location, date, time, description, country, city, eventId;
    Double price;
    Retrofit retrofit;
    EventService eventService;
    SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userId", "0");
        EventResponse myObject = (EventResponse) intent.getSerializableExtra("eventObj");
        eventTitle = findViewById(R.id.event_title);
        eventAddress = findViewById(R.id.event_address);
        eventPrice = findViewById(R.id.event_price);
        eventLocation = findViewById(R.id.location_text);
        eventTime = findViewById(R.id.time_text);
        eventDate = findViewById(R.id.date_text);
        eventDescription = findViewById(R.id.event_description);
        eventPrice = findViewById(R.id.event_price);
        buttonBook = findViewById(R.id.buttonBook);

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



        go_back = findViewById(R.id.back_arrow2);
        go_back.setOnClickListener(view -> {
            Intent intent1 = new Intent(EventDetailsActivity.this, EventsActivity.class);
            startActivity(intent1);
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService = retrofit.create(EventService.class);

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<BookEventResponse> call = eventService.bookEvent(eventID, userID);
                call.enqueue(new Callback<BookEventResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BookEventResponse> call, Response<BookEventResponse> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            // Handle successful response here
                            BookEventResponse responseData = response.body();
                            assert responseData != null;

                            if(responseData != null){
                                Toast.makeText(EventDetailsActivity.this, "Your ticket has been booked successfully", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(EventDetailsActivity.this, BookedSuccessfully.class);
                                intent1.putExtra("ticketID", responseData.getTicketId());
                                intent1.putExtra("eventObj", myObject);
                                intent1.putExtra("ticketStatus", responseData.getStatus());
                                startActivity(intent1);
                                finish();


                            }
                        } else {

                            /**
                             * If status is > 200
                             */
                            if (response.errorBody() != null) {
//                        try {
//                            String errorResponse = response.errorBody().string();
//                            ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
//                            String errorMessage = error.getMessage();
//                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                            }
                        }
                    }

                    /**
                     * If request failed
                     */
                    @Override
                    public void onFailure(Call<BookEventResponse> call, Throwable t) {
                        Toast.makeText(EventDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}