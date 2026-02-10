package com.weatherAPI.Dto;

import java.time.Instant;

public record WeatherResponse(
        String city,
        double temperatureC,
        String condition,
        int humidity,
        double windSpeed,
        Instant fetchedAt,
        String source
) { }
