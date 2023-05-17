package me.jdvp.androidaspectexample.activity.admin;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeRequest;
import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.weather.WeatherResponse;
import me.jdvp.androidaspectexample.Adapters.AdminEventTypeAdapter;
import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Interface.AdminService;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.Interface.WeatherApi;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.account.Register;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminHomeFragment extends Fragment {
    private EventService eventService;
    private AdminService adminService;
    private Retrofit retrofit;
    private TextView weatherTemp;
    EditText enter_new_type;
    Button add_newType_button;
    RecyclerView adminEventTypeRecyclerView;
    AdminEventTypeAdapter adminEventTypeAdapter;
    List<EventTypeResponse> eventTypes;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
         TextView admin_name_textView = view.findViewById(R.id.admin_name);
        String admin_name = "Admin"; // fetched admin name
        admin_name_textView.setText("Hi, "+ admin_name);
        weatherTemp=view.findViewById(R.id.weather_id);
        add_newType_button = view.findViewById(R.id.add_newType_button);
        enter_new_type = view.findViewById(R.id.enter_new_type);
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ApiUrls.WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit2.create(WeatherApi.class);
        Call<WeatherResponse> callWeather = weatherApi.getWeather("30.033333","31.233334", ApiUrls.API_KEY,"metric");
        callWeather.enqueue(new Callback<WeatherResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    weatherTemp.setText(weatherResponse.getList().get(0).getMain().getTemp()+"°C");
                } else {
                    Toast.makeText(getActivity(), "Cannot get the weather temperature", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService = retrofit.create(EventService.class);

        Call<List<EventTypeResponse>> call = eventService.getEventTypes();
        call.enqueue(new Callback<List<EventTypeResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventTypeResponse>> call, Response<List<EventTypeResponse>> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    // Handle successful response here
                    List<EventTypeResponse> responseData = response.body();
                    assert responseData != null;

                    if(!responseData.isEmpty()){
                        eventTypes = responseData;
                        adminEventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.admin_events_type_recyclerView);
                        adminEventTypeAdapter = new AdminEventTypeAdapter(getActivity(), eventTypes);
                        adminEventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                        adminEventTypeRecyclerView.setAdapter(adminEventTypeAdapter);

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
            public void onFailure(Call<List<EventTypeResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        add_newType_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter_new_type.getText().equals("")) {
                    Toast.makeText(getActivity(), "Please enter an event type", Toast.LENGTH_LONG).show();
                    return;
                }

                retrofit = new Retrofit.Builder()
                        .baseUrl(ApiUrls.ADMIN_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                adminService = retrofit.create(AdminService.class);
                AddEventTypeRequest addEventTypeRequest = new AddEventTypeRequest(enter_new_type.getText().toString());
                Call<AddEventTypeResponse> call = adminService.addEventType(addEventTypeRequest);
                call.enqueue(new Callback<AddEventTypeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<AddEventTypeResponse> call, Response<AddEventTypeResponse> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            // Handle successful response here
                            AddEventTypeResponse responseData = response.body();
                            assert responseData != null;
                            Toast.makeText(getContext(), "New Event Type is Added Successfully", Toast.LENGTH_LONG).show();

                            // Fetch the updated event types
                            fetchEventTypes();
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
                    public void onFailure(Call<AddEventTypeResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });





        return view;
    }
    private void fetchEventTypes() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService = retrofit.create(EventService.class);

        Call<List<EventTypeResponse>> call = eventService.getEventTypes();
        call.enqueue(new Callback<List<EventTypeResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventTypeResponse>> call, Response<List<EventTypeResponse>> response) {

                /**
                 * If status 200 is received
                 */
                if (response.isSuccessful()) {
                    // Handle successful response here
                    List<EventTypeResponse> responseData = response.body();
                    assert responseData != null;

                    if (!responseData.isEmpty()) {
                        eventTypes = responseData;
                        adminEventTypeAdapter.updateData(eventTypes); // Update the adapter's dataset
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
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<EventTypeResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

