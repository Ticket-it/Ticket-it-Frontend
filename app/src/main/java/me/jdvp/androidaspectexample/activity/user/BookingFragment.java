package me.jdvp.androidaspectexample.activity.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import me.jdvp.androidaspectexample.APIModel.Events.HistoryMainResponse;
import me.jdvp.androidaspectexample.APIModel.Events.TicketsHistoryResponse;
import me.jdvp.androidaspectexample.Adapters.EventHistoryAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BookingFragment extends Fragment {

    RecyclerView eventsHistoryRecyclerView;
    EventHistoryAdapter eventHistoryAdapter;
    SharedPreferences sharedPreferences;
    Retrofit retrofit;
    EventService eventService;
    @SuppressLint("MissingInflatedId")
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "0");

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
                            eventsHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.events_history_recycler_view);
                            eventHistoryAdapter = new EventHistoryAdapter(getActivity(), listHistory);
                            eventsHistoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                            eventsHistoryRecyclerView.setAdapter(eventHistoryAdapter);
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
                    public void onFailure(Call<HistoryMainResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



        return view;
    }
}