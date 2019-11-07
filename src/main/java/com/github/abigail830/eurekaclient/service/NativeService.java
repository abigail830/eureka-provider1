package com.github.abigail830.eurekaclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NativeService {

    public String getProviderName(String providerName){
//        log.info("This is via load balanced Method");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange("http://localhost:8081/name",
                HttpMethod.GET,
                null,
                String.class,
                providerName).getBody();

    }
}
