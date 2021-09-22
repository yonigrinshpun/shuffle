package com.ezbob.shuffle.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Log
@Configuration
@PropertySource("classpath:application.properties")
public class ServiceLog {
    private final RestTemplate restTemplate;

    private final WebClient.Builder webClientBuilder;

    @Value("${url:http://localhost:8082/service-log}")
    private String url;

    @Value("${url.error:http://localhost:8082/service-error-log}")
    private String urlError;

    public ServiceLog(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    public void sendLog(String logMessage){
        restTemplate.postForObject(url,logMessage, String.class);
    }
    public void sendErrorLog(String logMessage){
        restTemplate.postForObject(urlError,logMessage, String.class);
    }

    public void asyncSendLog(String logMessage){
        log.info("asyncSendLog");
        webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(logMessage);
    }
}
