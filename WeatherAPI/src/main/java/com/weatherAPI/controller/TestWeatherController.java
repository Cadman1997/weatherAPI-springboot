package com.weatherAPI.controller;


import com.weatherAPI.client.OpenWeatherClient;
import com.weatherAPI.client.dto.CurrentWeatherRaw;
import com.weatherAPI.client.dto.GeoResult;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/test-weather")
public class TestWeatherController {

    private final OpenWeatherClient client;

    public TestWeatherController(OpenWeatherClient client) {
        this.client = client;
    }

    @GetMapping
    public CurrentWeatherRaw test(@RequestParam @NotBlank String city) {
        GeoResult geo = client.geocodecity(city);
        return client.getCurrentWeather(geo.lat(), geo.lon());
    }
}
