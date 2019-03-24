package com.github.abigail830.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryClientApplication.class, args);
	}

}
