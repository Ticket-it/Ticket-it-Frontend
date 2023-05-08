package me.jdvp.androidaspectexample.activity.agent;

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

import me.jdvp.androidaspectexample.Adapters.AgentEventTypeAdapter;
import me.jdvp.androidaspectexample.Adapters.EventTypeAdapter;
import me.jdvp.androidaspectexample.Models.EventModel;
import me.jdvp.androidaspectexample.Models.EventTypeModel;
import me.jdvp.androidaspectexample.R;


public class AgentHomeFragment extends Fragment {

    RecyclerView agentEventTypeRecyclerView;
    AgentEventTypeAdapter agentEventTypeAdapter;
    ArrayList<EventTypeModel> eventTypes;

    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agent_home, container, false);


        TextView agent_name_textView = view.findViewById(R.id.agent_name);
        String agent_name = "Agent"; // fetched agent name
        agent_name_textView.setText("Hi, "+ agent_name);

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

        agentEventTypeRecyclerView = (RecyclerView) view.findViewById(R.id.agent_events_type_recyclerView);
        agentEventTypeAdapter = new AgentEventTypeAdapter(getActivity(), eventTypes);
        agentEventTypeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        agentEventTypeRecyclerView.setAdapter(agentEventTypeAdapter);

        return view;
    }
}