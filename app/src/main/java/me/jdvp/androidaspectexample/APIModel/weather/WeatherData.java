package me.jdvp.androidaspectexample.APIModel.weather;

public class WeatherData {
    private MainWeatherInfo main;

    public MainWeatherInfo getMain() {
        return main;
    }

    public WeatherData( MainWeatherInfo main) {
        this.main = main;
    }
}