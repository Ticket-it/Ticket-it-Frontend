package me.jdvp.androidaspectexample.Interface;

import me.jdvp.androidaspectexample.APIModel.weather.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("forecast?")
    Call<WeatherResponse> getWeather(@Query("lat") String lat,@Query("lon") String lon, @Query("appid") String apiKey,@Query("units") String metric);
}
