package me.jdvp.androidaspectexample.activity.user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.Events.EventTypeResponse;
import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Interface.EventService;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {


    RecyclerView eventTypeRecyclerView;
    EventTypeAdapter eventTypeAdapter;
    List<EventTypeResponse> eventTypes;
    private EventService eventService;
    private Retrofit retrofit;
    private TextView user_tv;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        user_tv=view.findViewById(R.id.user_name);
        String name = getActivity().getIntent().getStringExtra("name"); // Replace "name" with the same key used in the sending activity
        if (name != null) {
            user_tv.setText("Hi, "+ name);

        }

        ArrayList<EventModel> events = new ArrayList<>();
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));
        events.add(new EventModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt"));

        eventTypes = new ArrayList<>();
//        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
//        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
//        eventTypes.add(new EventTypeModel("@drawable/event_img",events));
//        eventTypes.add(new EventTypeModel("@drawable/event_img",events));

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
                        eventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.events_type_recyclerView);
                        eventTypeAdapter = new EventTypeAdapter(getActivity(), eventTypes);
                        eventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                        eventTypeRecyclerView.setAdapter(eventTypeAdapter);

                    }
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
            public void onFailure(Call<List<EventTypeResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}