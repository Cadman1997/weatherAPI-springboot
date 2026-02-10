package com.weatherAPI.client.dto;

public record GeoResult(
        String name,
        double lat,
        double lon,
        String country,
        String state
) {
}
