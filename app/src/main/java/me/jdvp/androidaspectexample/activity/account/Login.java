package me.jdvp.androidaspectexample.activity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import me.jdvp.androidaspectexample.Interface.LoginService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.activity.admin.AdminHome;
import me.jdvp.androidaspectexample.activity.agent.AgentHome;
import me.jdvp.androidaspectexample.activity.user.HomeActivity;
import me.jdvp.androidaspectexample.config.ApiUrls;
import me.jdvp.androidaspectexample.model.error.ErrorResponse;
import me.jdvp.androidaspectexample.model.user.LoginRequest;
import me.jdvp.androidaspectexample.model.user.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private TextView sign_up_btn;
    private TextView email_tv;
    private TextView password_tv;
    private Button sign_in_btn;
    private Retrofit retrofit;
    private LoginService loginService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        sign_up_btn = findViewById(R.id.sign_up_tv);
        email_tv = findViewById(R.id.email_tv);
        password_tv = findViewById(R.id.password_tv);
        sign_in_btn=findViewById(R.id.sign_in_btn);


        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginService = retrofit.create(LoginService.class);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(loginService,sharedPreferences);

            }
        });
    }

    public void signIn(LoginService loginService,SharedPreferences sharedPreferences){
        String email = email_tv.getText().toString();
        String password = password_tv.getText().toString();

        if (email_tv.equals("") || password_tv.equals("")) {
            Toast.makeText(Login.this, "Please enter the missing fields", Toast.LENGTH_LONG).show();
            return;
        }

        LoginRequest loginUserRequest = new LoginRequest(email, password);
        Call<LoginResponse> call = loginService.logIn(loginUserRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response here
                    LoginResponse responseData = response.body();
                    assert responseData != null;
                    if(responseData.getMessage().equals("true")){
                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userId", responseData.getUserId());

                        if(responseData.getType().equals("user")){
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.putExtra("name", responseData.getName());
                            startActivity(intent);
                            finish();
                        }
                        else if(responseData.getType().equals("admin")){
                            Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), AgentHome.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponse = response.errorBody().string();
                            ErrorResponse error = new Gson().fromJson(errorResponse, ErrorResponse.class);
                            String errorMessage = error.getMessage();
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
