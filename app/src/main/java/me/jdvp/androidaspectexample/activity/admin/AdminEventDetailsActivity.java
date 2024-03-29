package me.jdvp.androidaspectexample.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeRequest;
import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.admin.DeleteResponse;
import me.jdvp.androidaspectexample.APIModel.admin.EditEventResponse;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventDetails;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventTypeResponse;
import me.jdvp.androidaspectexample.Adapters.AdminEventTypeAdapter;
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
    TextView  num_tickets_tv,event_image_url,eventTitle, eventAddress, eventPrice, eventLocation, eventTime, eventDate, eventDescription;
    String eventTypeName,eventID, title, image, location, date, time, description, country, city, eventTypeID;
    Double price;
    int num_tickets;
    SharedPreferences sharedPreferences;
    Retrofit retrofit;
    AdminService adminService;
    ImageView img;
    Button saveButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_details);

        Intent intent = getIntent();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.ADMIN_URL)
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
        saveButton = findViewById(R.id.save_button);
        img = findViewById(R.id.image_event_details);
        event_image_url=findViewById(R.id.event_image_url);
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
        eventTypeID = intent.getStringExtra("eventTypeId");
        eventTypeName = intent.getStringExtra("eventTypeName");
        eventTypeName = intent.getStringExtra("eventTypeName");
        num_tickets= intent.getIntExtra("num_tickets",0);

        if(num_tickets>0){
            eventLocation.setText(city + ", " + country);
        }

        Picasso.get().load(image).into(img);
        eventTitle.setText(title);
        eventAddress.setText(location);
        eventTime.setText(time);
        eventDate.setText(date);
        eventDescription.setText(description);
        eventPrice.setText(price.toString());
        event_image_url.setText(image);
        num_tickets_tv.setText(String.valueOf(num_tickets));

        if(eventID == null){
            delete_image.setVisibility(View.INVISIBLE);
        }
        else{
            delete_image.setVisibility(View.VISIBLE);
        }

        go_back = findViewById(R.id.back_arrow3);
        go_back.setOnClickListener(view -> {
            finish();
        });

        Log.e("eventId", Objects.requireNonNullElse(eventID, "null value"));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<EditEventResponse> call;
                EventDetails eventDetails=new EventDetails(
                        Integer.parseInt(String.valueOf(num_tickets_tv.getText())),
                        Arrays.asList(eventLocation.getText().toString().split(",")).get(0),
                        Arrays.asList(eventLocation.getText().toString().split(",")).get(1),
                        String.valueOf(eventDate.getText()),
                        String.valueOf(eventDescription.getText()),
                        String.valueOf(eventTitle.getText()),
                        String.valueOf(event_image_url.getText()),
                        String.valueOf(eventAddress.getText()),
                        Double.parseDouble(String.valueOf(eventPrice.getText())),
                        String.valueOf(eventTime.getText()),
                        eventTypeID
                );
                if(eventID == null){
                    call = adminService.addEvent(eventDetails);
                }
                else{
                    call = adminService.editEvent(eventID,eventDetails);
                }

                call.enqueue(new Callback<EditEventResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<EditEventResponse> call, Response<EditEventResponse> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            // Handle successful response here
                            EditEventResponse responseData = response.body();
                            assert responseData != null;

                            if(eventID == null){
                                Toast.makeText(AdminEventDetailsActivity.this, "Event is Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AdminEventDetailsActivity.this, "Event is Edited Successfully", Toast.LENGTH_SHORT).show();
                            }

                            Intent intent1=new Intent(AdminEventDetailsActivity.this,AdminEventsActivity.class);
                            intent1.putExtra("eventTypeId",eventTypeID);
                            intent1.putExtra("eventTypeName",eventTypeName);
                            intent1.putExtra("event_img", image);

                            startActivity(intent1);
                            finish();
                        } else {
                            /**
                             * If status is > 200
                             */
                            if (response.errorBody() != null) {
                                try {
                                    String errorResponse = response.errorBody().string();
                                    ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
                                    String errorMessage = error.getMessage();
                                    Toast.makeText(AdminEventDetailsActivity.this, errorResponse, Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    /**
                     * If request failed
                     */
                    @Override
                    public void onFailure(Call<EditEventResponse> call, Throwable t) {
                        Toast.makeText(AdminEventDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        delete_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try {
                  Call<DeleteResponse> call = adminService.deleteEvent(eventID);
                  call.enqueue(new Callback<DeleteResponse>() {
                      @Override
                      public void onResponse(@NonNull Call<DeleteResponse> call, Response<DeleteResponse> response) {

                          /**
                           * If status 200 is received
                           */
                          if (response.isSuccessful()) {
                              // Handle successful response here
                              DeleteResponse responseData = response.body();
                              assert responseData != null;

                              Toast.makeText(AdminEventDetailsActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                              Intent intent1=new Intent(AdminEventDetailsActivity.this,AdminEventsActivity.class);
                              intent1.putExtra("eventTypeId",eventTypeID);
                              intent1.putExtra("eventTypeName",eventTypeName);
                              startActivity(intent1);
                              finish();
                          } else {

                              /**
                               * If status is > 200
                               */
                              if (response.errorBody() != null) {
                                  try {
                                      String errorResponse = response.errorBody().string();
                                      ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
                                      String errorMessage = error.getMessage();
                                      Toast.makeText(AdminEventDetailsActivity.this, errorResponse, Toast.LENGTH_LONG).show();
                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }
                      }

                      /**
                       * If request failed
                       */
                      @Override
                      public void onFailure(Call<DeleteResponse> call, Throwable t) {
                          Toast.makeText(AdminEventDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                      }
                  });

              } catch (Error error){
//                  Log.e(error.toString());
              }
            }
        });
    }
}