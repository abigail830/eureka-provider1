package com.github.abigail830.eurekaclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

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

    @HystrixCommand
    public String getProviderNameWithCircuitBreak(String providerName){
//        log.info("This is via load balanced Method");
        randomDelay();
        return restTemplate.exchange("http://{providerName}/name",
                HttpMethod.GET,
                null,
                String.class,
                providerName).getBody();
    }

    private void sleep(){
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            log.warn("Interrupted thread.sleep()");
        }
    }

    private void randomDelay(){
        Random random = new Random();
        final int rand = random.nextInt(3) + 1;
        if(rand==3)sleep();
    }
}
