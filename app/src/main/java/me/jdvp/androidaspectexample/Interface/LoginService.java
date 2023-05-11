package me.jdvp.androidaspectexample.Interface;

import me.jdvp.androidaspectexample.APIModel.user.CreateUserRequest;
import me.jdvp.androidaspectexample.APIModel.user.CreateUserResponse;
import me.jdvp.androidaspectexample.APIModel.user.LoginRequest;
import me.jdvp.androidaspectexample.APIModel.user.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * This interface has the required functions to login and register
 */
public interface LoginService {

    @POST("create")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest user);

    @POST("login")
    Call<LoginResponse> logIn(@Body LoginRequest user);

}
