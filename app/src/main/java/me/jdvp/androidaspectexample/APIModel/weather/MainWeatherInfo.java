package me.jdvp.androidaspectexample.APIModel.weather;

public class MainWeatherInfo {
    private float temp;
    private int humidity;

    public float getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public MainWeatherInfo(float temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }
}