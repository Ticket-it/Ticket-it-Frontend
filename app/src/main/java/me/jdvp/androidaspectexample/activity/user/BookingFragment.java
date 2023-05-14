package me.jdvp.androidaspectexample.activity.user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import me.jdvp.androidaspectexample.Adapters.AdminEventAdapter;
import me.jdvp.androidaspectexample.Adapters.AdminEventTypeAdapter;
import me.jdvp.androidaspectexample.Adapters.EventHistoryAdapter;
import me.jdvp.androidaspectexample.Models.EventHistoryModel;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;


public class BookingFragment extends Fragment {

    RecyclerView eventsHistoryRecyclerView;
    EventHistoryAdapter eventHistoryAdapter;
    ArrayList<EventHistoryModel> events = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);


        events.add(new EventHistoryModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt","confirmed"));
        events.add(new EventHistoryModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt","confirmed"));
        events.add(new EventHistoryModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt","confirmed"));
        events.add(new EventHistoryModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt","confirmed"));
        events.add(new EventHistoryModel("Amr Diab’s New Year", "Family Park, Fifth Settlement", "@drawable/event_img", 750, "Amr Diab is one of the most eminent Arabic pop stars in the Arabic world and a Guinness World Record Holder, Best Selling Middle Eastern Artist, 7 times winner of World Music Awards, 5 Platinum Record Awards & 6 African Music Awards.", "28/05/2023", "07:30 PM", "Cairo, Egypt","confirmed"));



        eventsHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.events_history_recycler_view);
        eventHistoryAdapter = new EventHistoryAdapter(getActivity(), events);
        eventsHistoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        eventsHistoryRecyclerView.setAdapter(eventHistoryAdapter);


        return view;
    }
}