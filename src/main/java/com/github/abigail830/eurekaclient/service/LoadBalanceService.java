package com.github.abigail830.eurekaclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class LoadBalanceService {

    @Autowired
    private RestTemplate restTemplate;

    public String getProviderName(String providerName){
        log.info("This is via load balanced Method");
        return restTemplate.exchange("http://{providerName}/name",
                HttpMethod.GET,
                null,
                String.class,
                providerName).getBody();

    }
}
