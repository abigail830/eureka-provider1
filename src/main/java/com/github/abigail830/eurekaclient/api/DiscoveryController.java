package com.github.abigail830.eurekaclient.api;

import com.github.abigail830.eurekaclient.service.DiscoveryService;
import com.github.abigail830.eurekaclient.service.LoadBalanceService;
import com.github.abigail830.eurekaclient.service.NameFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DiscoveryController {

    @Autowired
    DiscoveryService discoveryService;

    @Autowired
    LoadBalanceService loadBalanceService;

    @Autowired
    NameFeignClient nameFeignClient;

    @GetMapping("/discovery/{providerName}")
    public String discoveryProviderName(@PathVariable String providerName){
        return discoveryService.getProviderName(providerName);

    }

    @GetMapping("/load-balance/{providerName}")
    public String loadBalanceProviderName(@PathVariable String providerName){
        return loadBalanceService.getProviderName(providerName);
    }

    @GetMapping("/circuit-break/{providerName}")
    public String loadBalanceProviderNameWithCB(@PathVariable String providerName){
        return loadBalanceService.getProviderNameWithCircuitBreak(providerName);
    }

    @GetMapping("/feign/{providerName}")
    public String feignProviderName(@PathVariable String providerName){
        if (providerName.equals("eureka-consumer1"))
            return nameFeignClient.getName();
        else
            return "unknown provider";
    }
}
