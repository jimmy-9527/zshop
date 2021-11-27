package com.ken.zshop.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableConfigurationProperties(ThreadPoolProperties.class)
@Configuration
public class ThreadPoolConfig {


    @Bean
    public ThreadPoolExecutor executor(ThreadPoolProperties poolProperties){
        return new ThreadPoolExecutor(
                poolProperties.getCoreSize(),
                poolProperties.getMaxSize(),
                poolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

}

