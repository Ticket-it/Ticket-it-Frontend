package me.jdvp.androidaspectexample.activity.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.Adapters.AgentEventAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgentEventsActivity extends AppCompatActivity {

    RecyclerView agentEventsRecyclerView;
    AgentEventAdapter agentEventAdapter;
    ImageView back_btn;
    List<EventResponse> events;
    String eventTypeId, EventTypeName;
    TextView pageTitle;
    Retrofit retrofit;
    EventService eventService;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_events);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        back_btn = findViewById(R.id.back_arrow);
        back_btn.setOnClickListener(view -> {
            startActivity(new Intent(AgentEventsActivity.this, AgentHome.class));
        });

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
                        agentEventsRecyclerView = findViewById(R.id.agent_events_recycler_view);
                        agentEventAdapter = new AgentEventAdapter(AgentEventsActivity.this, events);
                        agentEventsRecyclerView.setLayoutManager(new GridLayoutManager(AgentEventsActivity.this, 1));
                        agentEventsRecyclerView.setAdapter(agentEventAdapter);
                    }
                } else {

                    /**
                     * If status is > 200
                     */
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

            /**
             * If request failed
             */
            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                Toast.makeText(AgentEventsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        back_btn = findViewById(R.id.back_arrow);
        pageTitle = findViewById(R.id.event_type);
        pageTitle.setText(eventTypeName);
        back_btn.setOnClickListener(view -> {
            editor.remove("eventTypeId");
            editor.remove("eventTypeName");

            editor.apply();

            startActivity(new Intent(AgentEventsActivity.this, AgentHome.class));
        });
    }
}