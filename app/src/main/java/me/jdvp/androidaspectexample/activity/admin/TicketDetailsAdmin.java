package me.jdvp.androidaspectexample.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationRequest;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.Interface.AgentService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.AgentUserHistoryActivity;
import me.jdvp.androidaspectexample.activity.agent.EventAttendants;
import me.jdvp.androidaspectexample.activity.agent.TicketDetailsAgent;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketDetailsAdmin extends AppCompatActivity {
    TextView event_title, event_address, event_price, date_text, time_text, location_text, attName, attPhone, attEmail, ticketID;
    Button statusBtn,confirm_attendance, viewHistory;
    String eventTypeId,eventTypeName,image,userId, ticketId, eventId, userName;
    AdminService adminService;
    Retrofit retrofit;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details_admin);

        statusBtn = findViewById(R.id.statusBtn);
        confirm_attendance = findViewById(R.id.confirm_attendance);

        event_title = findViewById(R.id.event_title);
        event_address = findViewById(R.id.event_address);
        event_price = findViewById(R.id.event_price);
        date_text = findViewById(R.id.date_text);
        time_text = findViewById(R.id.time_text);
        location_text = findViewById(R.id.location_text);
        attName = findViewById(R.id.attName);
        attPhone = findViewById(R.id.attPhone);
        attEmail = findViewById(R.id.attEmail);
        ticketID = findViewById(R.id.ticketID);
        viewHistory = findViewById(R.id.agent_viewHistory_btn);
        back_button=findViewById(R.id.back_button);

        Intent intent = getIntent();
        ticketID.setText(intent.getStringExtra("ticketId"));
        statusBtn.setText(intent.getStringExtra("status"));
        attName.setText(intent.getStringExtra("name"));
        attEmail.setText(intent.getStringExtra("email"));
        attPhone.setText(intent.getStringExtra("mobileNo"));
        event_title.setText(intent.getStringExtra("title"));
        event_address.setText(intent.getStringExtra("location"));
        event_price.setText(intent.getStringExtra("price"));
        date_text.setText(intent.getStringExtra("date"));
        time_text.setText(intent.getStringExtra("time"));
        location_text.setText(intent.getStringExtra("city") + ", " + intent.getStringExtra("country"));

        userId = intent.getStringExtra("userId");
        ticketId = intent.getStringExtra("ticketId");
        eventId = intent.getStringExtra("eventID");
        image = intent.getStringExtra("event_img");
        eventTypeId=intent.getStringExtra("eventTypeId");
        userName = intent.getStringExtra("name");
        eventTypeName=intent.getStringExtra("eventTypeName");

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.ADMIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit.create(AdminService.class);

        confirm_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmationRequest request = new ConfirmationRequest("Approved");
                Call<ConfirmationResponse> call = adminService.confirmBooking(ticketId, request);
                call.enqueue(new Callback<ConfirmationResponse>() {
                    @Override
                    public void onResponse(@NonNull Call <ConfirmationResponse> call, Response<ConfirmationResponse> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            // Handle successful response here
                            ConfirmationResponse responseData = response.body();
                            assert responseData != null;

                            if(responseData.getMessage().equals("true")){
                                Toast.makeText(TicketDetailsAdmin.this, userName+" have been approved successfully", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(TicketDetailsAdmin.this, AdminBookingActivity.class);
                                intent1.putExtra("eventId", eventId);
                                intent1.putExtra("eventTypeId", eventTypeId);
                                intent1.putExtra("eventTypeName", eventTypeName);

                                startActivity(intent1);
                                finish();
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
                    public void onFailure(Call <ConfirmationResponse> call, Throwable t) {
                        Toast.makeText(TicketDetailsAdmin.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TicketDetailsAdmin.this, AdminBookingActivity.class);
                intent1.putExtra("eventId", eventId);
                intent1.putExtra("eventTypeId", eventTypeId);
                intent1.putExtra("eventTypeName", eventTypeName);

                startActivity(intent1);
                finish();

            }
        });

        viewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TicketDetailsAdmin.this, AdminUserHistoryActivity.class);
                intent1.putExtra("eventID", eventId);
                intent1.putExtra("userId", userId);
                intent1.putExtra("userName", userName);

                startActivity(intent1);

            }
        });
    }


}