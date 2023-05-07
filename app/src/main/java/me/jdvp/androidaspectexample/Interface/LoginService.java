package me.jdvp.androidaspectexample.Interface;

import me.jdvp.androidaspectexample.model.user.CreateUserRequest;
import me.jdvp.androidaspectexample.model.user.CreateUserResponse;
import me.jdvp.androidaspectexample.model.user.LoginRequest;
import me.jdvp.androidaspectexample.model.user.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("create")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest user);

    @POST("login")
    Call<LoginResponse> logIn(@Body LoginRequest user);

}
