package com.weatherAPI.config;

import com.weatherAPI.Dto.WeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, WeatherResponse> weatherRedisTemplate(
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper
    ){
        JacksonJsonRedisSerializer<WeatherResponse> valueSerializer =
                new JacksonJsonRedisSerializer<>(objectMapper, WeatherResponse.class);

        RedisTemplate<String, WeatherResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
