package com.ezbob.shuffle.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.print.DocFlavor;

@Component
@Log
public class ServiceLog {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static String url = "http://localhost:8082/service-log";

    public void sendLog(String logMessage){
        restTemplate.postForObject(url,logMessage, String.class);
    }

    public void asyncSendLog(String logMessage){
        log.info("asyncSendLog");
        webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(logMessage);
    }
}
