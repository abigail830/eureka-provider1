package com.github.abigail830.eurekaclient.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping
    public String getName(){
        return "Here is Eureka-Client With DiscoveryClient!";
    }
}
