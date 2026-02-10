package com.weatherAPI.controller;


import com.weatherAPI.client.OpenWeatherClient;
import com.weatherAPI.client.dto.GeoResult;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/geo")
public class GeoController {

    private final OpenWeatherClient client;

    public GeoController(OpenWeatherClient client) {
        this.client = client;
    }

    @GetMapping
    public GeoResult geocode(@RequestParam @NotBlank String city) {
        return client.geocodecity(city);
    }
}
