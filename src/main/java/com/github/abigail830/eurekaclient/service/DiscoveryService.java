package com.github.abigail830.eurekaclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getProviderName(String providerName){
        RestTemplate restTemplate = new RestTemplate();
        final List<ServiceInstance> instances = discoveryClient.getInstances(providerName);
        if(!instances.isEmpty()){
            final int index = new Random().nextInt(instances.size());
            final ServiceInstance serviceInstance = instances.get(index);
            String url = serviceInstance.getUri()+"/name";
            return restTemplate.exchange(url, HttpMethod.GET,null,String.class).getBody();
        }

        return "";
    }
}
