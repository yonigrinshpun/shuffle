package com.ezbob.shuffle.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log
@Configuration
@PropertySource("classpath:application.properties")
public class ServiceLog {
    private final RestTemplate restTemplate;

    @Value("${url:http://localhost:8082/service-log}")
    private String url;

    @Value("${url.error:http://localhost:8082/service-error-log}")
    private String urlError;

    public ServiceLog(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Async
    public void sendLog(String logMessage){
        restTemplate.postForObject(url,logMessage, String.class);
    }
    @Async
    public void sendErrorLog(String logMessage){
        restTemplate.postForObject(urlError,logMessage, String.class);
    }

}
