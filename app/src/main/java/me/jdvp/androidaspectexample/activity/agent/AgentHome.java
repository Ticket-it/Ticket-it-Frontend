package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import me.jdvp.androidaspectexample.R;

public class AgentHome extends AppCompatActivity {
    AgentHomeFragment agentHomeFragment = new AgentHomeFragment();
    //AgentBookingFragment agentBookingFragment = new AgentBookingFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, agentHomeFragment).commit();



    }
}