package com.ken.zshop.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.PipedReader;
import java.time.Duration;


@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate getRestTemplate(){
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(5));
        restTemplateBuilder.setReadTimeout(Duration.ofSeconds(5));
        return restTemplateBuilder.build();
    }

}

