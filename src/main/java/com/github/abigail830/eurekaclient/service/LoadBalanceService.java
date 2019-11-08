package com.github.abigail830.eurekaclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "getDefaultName"
    )
    public String getProviderNameWithCircuitBreak(String providerName){
        return restTemplate.exchange("http://{providerName}/name-delay",
                HttpMethod.GET,
                null,
                String.class,
                providerName).getBody();
    }

    public String getDefaultName(String providerName) {
        return "default name";
    }

}
