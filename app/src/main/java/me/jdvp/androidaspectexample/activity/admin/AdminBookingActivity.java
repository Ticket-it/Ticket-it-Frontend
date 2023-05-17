package me.jdvp.androidaspectexample.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Adapters.AttendantsAdapter;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.Interface.AgentService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.agent.EventAttendants;
import me.jdvp.androidaspectexample.activity.user.BookingFragment;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminBookingActivity extends AppCompatActivity {
    AdminBookingFragement bookingFragment = new AdminBookingFragement();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, bookingFragment).commit();





    }
}