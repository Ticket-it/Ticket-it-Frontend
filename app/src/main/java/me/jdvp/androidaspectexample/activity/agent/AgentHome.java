package me.jdvp.androidaspectexample.activity.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import me.jdvp.androidaspectexample.R;

public class AgentHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AgentHomeFragment agentHomeFragment = new AgentHomeFragment();
    AgentBookingFragment agentBookingFragment = new AgentBookingFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, agentHomeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, agentHomeFragment).commit();
                        return true ;
                    case R.id.booking:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, agentBookingFragment).commit();
                        return true ;
                }
                return false;
            }
        });
    }
}