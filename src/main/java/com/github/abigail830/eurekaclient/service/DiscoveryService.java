package com.github.abigail830.eurekaclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class DiscoveryService {


    private DiscoveryClient discoveryClient;

    @Autowired
    public DiscoveryService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public String getProviderName(String providerName){
//        log.info("This is via DiscoveryClient Method");
        RestTemplate restTemplate = new RestTemplate();
        final List<ServiceInstance> instances = discoveryClient.getInstances(providerName);
        if(!instances.isEmpty()){
            final ServiceInstance serviceInstance = instances.get(0);
//            log.info("{}", serviceInstance);

            String url = serviceInstance.getUri()+"/name";
            return restTemplate.exchange(url, HttpMethod.GET,null,String.class).getBody();
        }

        return "";
    }
}
