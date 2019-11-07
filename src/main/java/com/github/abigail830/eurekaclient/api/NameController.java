package com.github.abigail830.eurekaclient.api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class NameController {

    @Value("${spring.eureka.instance.hostname}")
    String name;

    @GetMapping
    public String getName(){
        System.out.println("Receive naming request");
        return "Here is Eureka-Client With DiscoveryClient: " + name;
    }
}
