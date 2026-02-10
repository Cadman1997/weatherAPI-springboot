package com.weatherAPI.client.dto;


import java.util.List;

public record CurrentWeatherRaw(
        List<Weather> weather,
        Main main,
        Wind wind
) {
    public record Weather(String main, String description) {}
    public record Main(double temp, int humidity) {}
    public record Wind(double speed) {}


}
