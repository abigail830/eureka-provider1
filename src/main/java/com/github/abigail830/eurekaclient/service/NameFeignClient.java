package com.github.abigail830.eurekaclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-consumer1")
public interface NameFeignClient {

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    String getName();
}
