package me.jdvp.androidaspectexample.activity.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.APIModel.weather.WeatherResponse;
import me.jdvp.androidaspectexample.Adapters.AdminEventTypeAdapter;
import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Interface.WeatherApi;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminHomeFragment extends Fragment {

    RecyclerView adminEventTypeRecyclerView;
    AdminEventTypeAdapter adminEventTypeAdapter;
    ArrayList<EventTypeModel> eventTypes;
    TextView weatherTemp;
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






        ArrayList<EventModel> events = new ArrayList<>();
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));

        eventTypes = new ArrayList<>();
        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
        eventTypes.add(new EventTypeModel("@drawable/event_img",events));

        adminEventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.admin_events_type_recyclerView);
        adminEventTypeAdapter = new AdminEventTypeAdapter(getActivity(), eventTypes);
        adminEventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adminEventTypeRecyclerView.setAdapter(adminEventTypeAdapter);


        return view;
    }
}