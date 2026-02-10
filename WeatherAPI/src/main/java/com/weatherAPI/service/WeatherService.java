package com.weatherAPI.service;

import com.weatherAPI.Dto.WeatherResponse;
import com.weatherAPI.cache.WeatherCacheService;
import com.weatherAPI.client.OpenWeatherClient;
import com.weatherAPI.client.dto.CurrentWeatherRaw;
import com.weatherAPI.client.dto.GeoResult;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class WeatherService {

    private final WeatherCacheService cache;



    private final OpenWeatherClient client;

    public WeatherService(WeatherCacheService cache, OpenWeatherClient client) {
        this.cache = cache;
        this.client = client;
    }

    public WeatherResponse getWeatherByCity(String city) {
        return cache.get(city).orElseGet(() -> {
            GeoResult geo = client.geocodecity(city);
            CurrentWeatherRaw raw = client.getCurrentWeather(geo.lat(), geo.lon());

            WeatherResponse response = mapToWeatherResponse(city, raw);

            cache.set(city, response);

            return response;
        });
    }


    private WeatherResponse mapToWeatherResponse(String city, CurrentWeatherRaw raw) {
        String condition = (raw.weather() != null && !raw.weather().isEmpty())
                ? raw.weather().get(0).main()
                : "unknown";

        double temp = raw.main() != null ? raw.main().temp() : Double.NaN;
        int humidity = raw.main() != null ? raw.main().humidity() : -1;
        double windSpeed = raw.wind() != null ? raw.wind().speed() : Double.NaN;

        return new WeatherResponse(
                city,
                temp,
                condition,
                humidity,
                windSpeed,
                Instant.now(),
                "OPENWEATHER"
        );
    }
}
