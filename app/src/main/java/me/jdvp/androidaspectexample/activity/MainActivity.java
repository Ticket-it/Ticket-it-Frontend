package me.jdvp.androidaspectexample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import me.jdvp.androidaspectexample.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Using findViewById so we don't have to worry about deprecations later
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);

        //The button ClickListener's below only have actions that create Toasts.
        //However, when you press the buttons at runtime, you will notice in LogCat
        //that messages are being logged as well. Those messages are being logged by
        //the Logger class as specified by aspects put in the AspectLogging file. The code
        //to do the logging is injected in-line at compile time

        //Not a lambda on purpose for demonstration purposes
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Button 1 clicked")
                        .show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Button 2 clicked")
                        .show();
            }
        });
    }
}
