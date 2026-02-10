package com.weatherAPI.client;


import com.weatherAPI.client.dto.CurrentWeatherRaw;
import com.weatherAPI.client.dto.GeoResult;
import com.weatherAPI.config.OpenWeatherProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;

import java.net.URI;
import java.util.List;


@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final OpenWeatherProperties props;

    public OpenWeatherClient(RestClient restClient, OpenWeatherProperties props) {
        this.restClient = restClient;
        this.props = props;
    }

    public GeoResult geocodecity(String city) {
        URI uri = UriComponentsBuilder
                .fromUriString(props.getBaseUrl())
                .path("/geo/1.0/direct")
                .queryParam("q", city)
                .queryParam("limit", 1)
                .queryParam("appid", props.getApikey())
                .build(true)
                .toUri();


        List<GeoResult> results = restClient.get()
                .uri(uri)
                .retrieve()
                .body(new  ParameterizedTypeReference<List<GeoResult>>() {});

        if (results == null || results.isEmpty()){
            throw new IllegalArgumentException("City not found: " + city);
        }
        return results.get(0);
    }

    public CurrentWeatherRaw getCurrentWeather(double lat, double lon) {
        URI uri = UriComponentsBuilder
                .fromUriString(props.getBaseUrl())
                .path("/data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", props.getApikey())
                .queryParam("unit", props.getUnits())
                .build(true)
                .toUri();

        System.out.println("CURRENT WEATHER URL =>" + uri);

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(CurrentWeatherRaw.class);
    }
}


