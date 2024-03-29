package me.jdvp.androidaspectexample.activity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import me.jdvp.androidaspectexample.Interface.LoginService;
import me.jdvp.androidaspectexample.R;
import me.jdvp.androidaspectexample.config.ApiUrls;
import me.jdvp.androidaspectexample.APIModel.error.ErrorResponse;
import me.jdvp.androidaspectexample.APIModel.user.CreateUserRequest;
import me.jdvp.androidaspectexample.APIModel.user.CreateUserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    /**
     * Here are private attributes to be used later
     */
    private TextView full_name_tv;
    private TextView mobile_tv;
    private TextView email_tv;
    private TextView password_tv;
    private TextView confirm_password_tv;
    private Button sign_in_btn;
    private TextView sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * Map view to the activity
         */
        full_name_tv = findViewById(R.id.full_name_tv);
        mobile_tv = findViewById(R.id.mobile_tv);
        email_tv = findViewById(R.id.email_tv);
        password_tv = findViewById(R.id.password_tv);
        confirm_password_tv = findViewById(R.id.confirm_password_tv);
        sign_in_btn = findViewById(R.id.home_btn);
        sign_up_btn = findViewById(R.id.sign_up_tv);

        /**
         * Define retrofit to make GET/POST messages
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrls.LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);

        /**
         * Triggers when user clicks sign in
         */
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        /**
         * Triggers when user clicks sign up
         */
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Get values from view
                 */
                String fullName = full_name_tv.getText().toString();
                String email = email_tv.getText().toString();
                String mobileNo = mobile_tv.getText().toString();
                String password = password_tv.getText().toString();
                String confirmPassword = confirm_password_tv.getText().toString();

                /**
                 * Validation
                 */
                if (fullName.equals("") || mobileNo.equals("") || email.equals("") || confirmPassword.equals("") || password.equals("")) {
                    Toast.makeText(Register.this, "Please enter the full details", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Passwords does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                /**
                 * Send POST request
                 */
                CreateUserRequest createUserRequest = new CreateUserRequest(email, password, fullName, mobileNo);
                Call<CreateUserResponse> call = loginService.createUser(createUserRequest);
                call.enqueue(new Callback<CreateUserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CreateUserResponse> call, Response<CreateUserResponse> response) {

                        /**
                         * If status 200 is received
                         */
                        if (response.isSuccessful()) {
                            // Handle successful response here
                            CreateUserResponse responseData = response.body();
                            assert responseData != null;
                            if(responseData.getMessage().equals("true")){
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            /**
                             * If status is > 200
                             */
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

                    /**
                     * If request failed
                     */
                    @Override
                    public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}