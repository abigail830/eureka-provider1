package com.github.abigail830.eurekaclient.service;

public class NameFeignClientImpl implements NameFeignClient {
    @Override
    public String getName(String providerName) {
        return "backup result";
    }
}
