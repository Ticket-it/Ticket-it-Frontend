package me.jdvp.androidaspectexample.APIModel.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private List<WeatherData> list;

    public List<WeatherData> getList() {
        return list;
    }

    public WeatherResponse(List<WeatherData> list) {

        this.list = list;
    }
}

