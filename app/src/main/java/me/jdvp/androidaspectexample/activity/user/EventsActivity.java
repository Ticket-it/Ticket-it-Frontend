package me.jdvp.androidaspectexample.activity.user;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.Events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.Events.EventTypeResponse;
import me.jdvp.androidaspectexample.Adapters.EventAdapter;
import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsActivity extends AppCompatActivity {

    RecyclerView eventsRecyclerView;
    EventAdapter eventAdapter;
    List<EventResponse> events;
    String eventTypeId, EventTypeName;
    ImageView back_btn;
    TextView pageTitle;
    Retrofit retrofit;
    EventService eventService;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setContentView(R.layout.activity_events);
        Intent intent = getIntent();

        eventTypeId = intent.getStringExtra("event_ID");
        EventTypeName = intent.getStringExtra("eventTypeName");

        if(eventTypeId!=null){
            editor.putString("eventTypeId", eventTypeId);
            editor.putString("eventTypeName", EventTypeName);
            editor.apply();
        }

        String value = sharedPreferences.getString("eventTypeId", "0");
        String eventTypeName = sharedPreferences.getString("eventTypeName", "");

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService = retrofit.create(EventService.class);

        Call<List<EventResponse>> call = eventService.getEvents(value);
        call.enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventResponse>> call, Response<List<EventResponse>> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    // Handle successful response here
                    List<EventResponse> responseData = response.body();
                    assert responseData != null;

                    if(!responseData.isEmpty()){
                        events = responseData;
                        eventsRecyclerView = findViewById(R.id.events_recycler_view);
                        eventAdapter = new EventAdapter(EventsActivity.this, events);
                        eventsRecyclerView.setLayoutManager(new GridLayoutManager(EventsActivity.this, 1));
                        eventsRecyclerView.setAdapter(eventAdapter);
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
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                Toast.makeText(EventsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        back_btn = findViewById(R.id.back_arrow);
        pageTitle = findViewById(R.id.event_type);
        pageTitle.setText(eventTypeName);
        back_btn.setOnClickListener(view -> {
            editor.remove("eventTypeId");
            editor.remove("eventTypeName");

            editor.apply();

            startActivity(new Intent(EventsActivity.this, HomeActivity.class));
        });
    }
}