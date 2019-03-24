package com.github.abigail830.eurekaclient.api;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping
    public String getName(){
        return "Here is Eureka-Client With DiscoveryClient!";
    }
}
