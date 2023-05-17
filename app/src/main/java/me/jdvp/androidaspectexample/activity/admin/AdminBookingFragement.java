package me.jdvp.androidaspectexample.activity.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.admin.ApproveAllResponse;
import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.Adapters.AdminBookingAdapter;
import me.jdvp.androidaspectexample.Adapters.AttendantsAdapter;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminBookingFragement extends Fragment {
    String eventID;
    RecyclerView attendantRecyclerView;
    AdminBookingAdapter attendantsAdapter;
    ArrayList<AttendanceResponse> attendants = new ArrayList<>();
    Retrofit retrofit, retrofit2;
    AdminService adminService;
    Button accept_all_button;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_booking, container, false);
        Intent intent = getActivity().getIntent();
        eventID = intent.getStringExtra("eventId");
        accept_all_button = view.findViewById(R.id.accept_all_button);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.ADMIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit.create(AdminService.class);
        Call<ArrayList<AttendanceResponse>> call = adminService.getTickets(eventID);
        call.enqueue(new Callback<ArrayList<AttendanceResponse>>() {
            @Override
            public void onResponse(@NonNull Call <ArrayList<AttendanceResponse>> call, Response<ArrayList<AttendanceResponse>> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    Log.d("res", "onResponse: ");
                    // Handle successful response here
                    ArrayList <AttendanceResponse> responseData = response.body();
                    assert responseData != null;
                    attendantRecyclerView = view.findViewById(R.id.admin_booking_recycler_view);
                    attendantsAdapter = new AdminBookingAdapter(getActivity(), responseData);
                    attendantRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
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
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




        retrofit2 = new Retrofit.Builder()
                .baseUrl(ApiUrls.ADMIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        adminService = retrofit2.create(AdminService.class);
        accept_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<ApproveAllResponse>> call = adminService.approveTickets(eventID);
                call.enqueue(new Callback<List<ApproveAllResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call <List<ApproveAllResponse>> call, Response<List<ApproveAllResponse>> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            Log.d("res", "onResponse: ");
                            // Handle successful response here
                            List <ApproveAllResponse> responseData = response.body();
                            assert responseData != null;
                            Toast.makeText(getContext(), "All bookings are approved", Toast.LENGTH_LONG).show();
                            // Clear the RecyclerView dataset
                            responseData.clear();
                            // Notify the adapter of the data change
                            attendantsAdapter.updateData(responseData);


                        } else {

                            /**
                             * If status is > 200
                             */
                            if (response.errorBody() != null) {
                                try {
                                    String errorResponse = response.errorBody().string();
                                    ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
                                    String errorMessage = error.getMessage();
                                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
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
                    public void onFailure(Call <List<ApproveAllResponse>> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        return view;
    }
}