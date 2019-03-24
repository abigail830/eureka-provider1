package com.github.abigail830.eurekaclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-provider1")
public interface NameFeignClient {

    @RequestMapping(value = "/{providerName}/name", method = RequestMethod.GET)
    String getName(@PathVariable("providerName") String providerName);
}
