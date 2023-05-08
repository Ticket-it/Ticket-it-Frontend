package me.jdvp.androidaspectexample.activity.user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;


public class HomeFragment extends Fragment {


    RecyclerView eventTypeRecyclerView;
    EventTypeAdapter eventTypeAdapter;
    ArrayList<EventTypeModel> eventTypes;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView user_name_textView = view.findViewById(R.id.user_name);
        String user_name = "Mostafa"; // fetched username
        user_name_textView.setText("Hi, "+ user_name);

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

        eventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.events_type_recyclerView);
        eventTypeAdapter = new EventTypeAdapter(getActivity(), eventTypes);
        eventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        eventTypeRecyclerView.setAdapter(eventTypeAdapter);


        return view;
    }
}