package me.jdvp.androidaspectexample.activity.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Adapters.AttendantsAdapter;
import me.jdvp.androidaspectexample.Interface.AgentService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventAttendants extends AppCompatActivity {
    String eventID;
    RecyclerView attendantRecyclerView;
    AttendantsAdapter attendantsAdapter;
    ArrayList<AttendanceResponse> attendants = new ArrayList<>();
    Retrofit retrofit;
    AgentService agentService;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendants);
        Intent intent = getIntent();
        eventID = intent.getStringExtra("eventId");

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.AGENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        agentService = retrofit.create(AgentService.class);
        Call<ArrayList<AttendanceResponse>> call = agentService.getAttendants(eventID);
        call.enqueue(new Callback<ArrayList<AttendanceResponse>>() {
            @Override
            public void onResponse(@NonNull Call <ArrayList<AttendanceResponse>> call, Response<ArrayList<AttendanceResponse>> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    // Handle successful response here
                    ArrayList <AttendanceResponse> responseData = response.body();
                    assert responseData != null;
                    attendantRecyclerView = findViewById(R.id.attendant_recyclerView);
                    attendantsAdapter = new AttendantsAdapter(EventAttendants.this, responseData);
                    attendantRecyclerView.setLayoutManager(new GridLayoutManager(EventAttendants.this, 1));
                    attendantRecyclerView.setAdapter(attendantsAdapter);
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
            public void onFailure(Call <ArrayList<AttendanceResponse>> call, Throwable t) {
                Toast.makeText(EventAttendants.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}