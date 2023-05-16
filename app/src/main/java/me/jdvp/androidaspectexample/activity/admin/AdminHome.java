package me.jdvp.androidaspectexample.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import me.jdvp.androidaspectexample.R;

public class AdminHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    AdminBookingFragement adminBookingFragement = new AdminBookingFragement();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, adminHomeFragment).commit();

    }
}