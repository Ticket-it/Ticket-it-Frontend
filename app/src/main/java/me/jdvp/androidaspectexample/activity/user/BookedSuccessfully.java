package me.jdvp.androidaspectexample.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.R;

public class BookedSuccessfully extends AppCompatActivity {
    Button viewTicket, homeBtn;
    String status, ticketID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_successfully);
        viewTicket = findViewById(R.id.view_ticket_btn);
        homeBtn = findViewById(R.id.home_btn);
        Intent intent = getIntent();

        EventResponse myObject = (EventResponse) intent.getSerializableExtra("eventObj");
        status = intent.getStringExtra("ticketStatus");
        ticketID = intent.getStringExtra("ticketID");



        viewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BookedSuccessfully.this, TicketDetails.class);
                intent1.putExtra("eventObj", myObject);
                intent1.putExtra("ticketStatus", status);
                intent1.putExtra("ticketID",ticketID );
                startActivity(intent1);
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BookedSuccessfully.this, HomeActivity.class);
                startActivity(intent1);
                finish();

            }
        });


    }
}