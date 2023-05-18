package me.jdvp.androidaspectexample.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.Adapters.AdminEventAdapter;
import me.jdvp.androidaspectexample.Adapters.EventAdapter;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.user.EventsActivity;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminEventsActivity extends AppCompatActivity {

    RecyclerView adminEventsRecyclerView;
    AdminEventAdapter adminEventAdapter;
    List<EventResponse> events;
    String eventTypeId, eventTypeName;
    ImageView back_btn;
    TextView pageTitle;
    Retrofit retrofit;
    AdminService adminService;

    Button addNewButton;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_events);
        Intent intent = getIntent();
        eventTypeId =  intent.getStringExtra("eventTypeId");
        eventTypeName =  intent.getStringExtra("eventTypeName");

        pageTitle=findViewById(R.id.admin_event_title);
        back_btn = findViewById(R.id.admin_back_arrow);
        pageTitle.setText(eventTypeName);
        addNewButton = findViewById(R.id.add_new_event);

        Toast.makeText(this, eventTypeId, Toast.LENGTH_SHORT).show();

        addNewButton.setOnClickListener(view -> {
            Intent intent1=new Intent(AdminEventsActivity.this, AdminEventDetailsActivity.class);
            intent1.putExtra("eventTypeId",eventTypeId);
            startActivity(intent1);
        });

        back_btn.setOnClickListener(view -> {
            finish();
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit.create(AdminService.class);

        Call<List<EventResponse>> call = adminService.getEvents(eventTypeId);
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
                        adminEventsRecyclerView = findViewById(R.id.admin_events_recycler_view);
                        adminEventAdapter = new AdminEventAdapter(AdminEventsActivity.this, events);
                        adminEventsRecyclerView.setLayoutManager(new GridLayoutManager(AdminEventsActivity.this, 1));
                        adminEventsRecyclerView.setAdapter(adminEventAdapter);
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
                Toast.makeText(AdminEventsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}