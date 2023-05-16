package me.jdvp.androidaspectexample.activity.agent;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.weather.WeatherResponse;
import me.jdvp.androidaspectexample.Adapters.AgentEventTypeAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.Interface.WeatherApi;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AgentHomeFragment extends Fragment {

    RecyclerView agentEventTypeRecyclerView;
    AgentEventTypeAdapter agentEventTypeAdapter;
    List<EventTypeResponse> eventTypes;
    Retrofit retrofit;
    EventService eventService;
    TextView weatherTemp;

    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agent_home, container, false);

        weatherTemp=view.findViewById(R.id.weather_id);

        TextView agent_name_textView = view.findViewById(R.id.agent_name);
        String agent_name = "Agent"; // fetched agent name
        agent_name_textView.setText("Hi, "+ agent_name);

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
                        agentEventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.agent_events_type_recyclerView);
                        agentEventTypeAdapter = new AgentEventTypeAdapter(getActivity(), eventTypes);
                        agentEventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                        agentEventTypeRecyclerView.setAdapter(agentEventTypeAdapter);

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



        return view;
    }
}