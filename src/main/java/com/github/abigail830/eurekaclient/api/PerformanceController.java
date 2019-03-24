package com.github.abigail830.eurekaclient.api;

import com.github.abigail830.eurekaclient.service.DiscoveryService;
import com.github.abigail830.eurekaclient.service.LoadBalanceService;
import com.github.abigail830.eurekaclient.service.NameFeignClient;
import com.github.abigail830.eurekaclient.service.NativeService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@Slf4j
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    DiscoveryService discoveryService;

    @Autowired
    LoadBalanceService loadBalanceService;

    @Autowired
    NameFeignClient nameFeignClient;

    @Autowired
    NativeService nativeService;

    int times=1000;

    @GetMapping("/discovery/{providerName}")
    public void discoveryProviderName(@PathVariable String providerName){
        Instant start = Instant.now();
        for(int i=0;i<times;i++){
            discoveryService.getProviderName(providerName);
        }
        Instant end = Instant.now();
        log.info("{}ms for {} times loop with discovery",
                Duration.between(start,end).toMillis(),times);
    }

    @GetMapping("/load-balance/{providerName}")
    public void loadBalanceProviderName(@PathVariable String providerName){
        Instant start = Instant.now();
        for(int i=0;i<times;i++) {
            loadBalanceService.getProviderName(providerName);
        }
        Instant end = Instant.now();
        log.info("{}ms for {} times loop with load-balance",
                Duration.between(start,end).toMillis(),times);
    }

    @GetMapping("/circuit-break/{providerName}")
    public void loadBalanceProviderNameWithCB(@PathVariable String providerName){
        Instant start = Instant.now();
        int exceptionTimes = 0;
        for(int i=0;i<times;i++) {
            try{
                final String result = loadBalanceService.getProviderNameWithCircuitBreak(providerName);
                log.info("{} - {}", i,result);
            }catch (HystrixRuntimeException e){
                log.warn("{} - HystrixRuntimeException happened {} times", i,exceptionTimes++);
            }

        }
        Instant end = Instant.now();
        log.info("{}ms for {} times loop with load-balance",
                Duration.between(start,end).toMillis(),times);
    }

    @GetMapping("/native/{providerName}")
    public void nativeProviderName(@PathVariable String providerName){
        Instant start = Instant.now();
        for(int i=0;i<times;i++) {
            nativeService.getProviderName(providerName);
        }
        Instant end = Instant.now();
        log.info("{}ms for {} times loop with native",
                Duration.between(start,end).toMillis(),times);
    }

    @GetMapping("/feign/{providerName}")
    public String feignProviderName(@PathVariable String providerName){
        return nameFeignClient.getName(providerName);
    }
}
