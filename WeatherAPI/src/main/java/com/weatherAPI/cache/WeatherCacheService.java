package com.weatherAPI.cache;

import com.weatherAPI.Dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class WeatherCacheService {

    private final RedisTemplate<String, WeatherResponse> redis;
    private final Duration ttl;

    public WeatherCacheService(
            RedisTemplate<String, WeatherResponse> weatherRedisTemplate,
            @Value("${cache.weather-ttl-seconds:43200}") long ttlSeconds
    ) {
        this.redis = weatherRedisTemplate;
        this.ttl = Duration.ofSeconds(ttlSeconds);
    }

    public Optional<WeatherResponse> get(String city) {
        return Optional.ofNullable(redis.opsForValue().get(key(city)));
    }

    public void set(String city, WeatherResponse response) {
        redis.opsForValue().set(key(city), response, ttl);
    }

    private String key(String city) {
        return "weather:v2:" + city.trim().toLowerCase();
    }
}
