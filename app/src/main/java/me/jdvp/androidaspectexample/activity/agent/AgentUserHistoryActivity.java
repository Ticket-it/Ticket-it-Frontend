package me.jdvp.androidaspectexample.activity.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.HistoryMainResponse;
import me.jdvp.androidaspectexample.APIModel.events.TicketsHistoryResponse;
import me.jdvp.androidaspectexample.Adapters.EventHistoryAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgentUserHistoryActivity extends AppCompatActivity {
    String userName, userId;
    RecyclerView eventsHistoryRecyclerView;
    EventHistoryAdapter eventHistoryAdapter;
    Retrofit retrofit;
    EventService eventService;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_user_history);
        title = findViewById(R.id.agent_history_title);


        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userId = intent.getStringExtra("userId");

        title.setText(userName + "'s" + " Tickets " + "History");

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService = retrofit.create(EventService.class);


        Call<HistoryMainResponse> call = eventService.getHistory(userId);
        call.enqueue(new Callback<HistoryMainResponse>() {
            @Override
            public void onResponse(@NonNull Call<HistoryMainResponse> call, Response<HistoryMainResponse> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    // Handle successful response here
                    HistoryMainResponse responseData = response.body();
                    assert responseData != null;
                    List<TicketsHistoryResponse> listHistory=responseData.getHistory().getTickets();
                    eventsHistoryRecyclerView = (RecyclerView) findViewById(R.id.events_history_recycler_view);
                    eventHistoryAdapter = new EventHistoryAdapter(AgentUserHistoryActivity.this, listHistory);
                    eventsHistoryRecyclerView.setLayoutManager(new GridLayoutManager(AgentUserHistoryActivity.this, 1));
                    eventsHistoryRecyclerView.setAdapter(eventHistoryAdapter);
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
            public void onFailure(Call<HistoryMainResponse> call, Throwable t) {
                Toast.makeText(AgentUserHistoryActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}