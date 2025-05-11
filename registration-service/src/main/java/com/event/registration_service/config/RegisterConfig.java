package com.event.registration_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RegisterConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
