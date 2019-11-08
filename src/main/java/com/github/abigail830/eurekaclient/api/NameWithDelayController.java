package com.github.abigail830.eurekaclient.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/name-delay")
public class NameWithDelayController {

    @Value("${spring.eureka.instance.hostname}")
    String name;

    @GetMapping
    public String getName() {
        log.info("Receive naming request");
        randomDelay();
        return "Here is Eureka-Client With DiscoveryClient: " + name;
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.warn("Interrupted thread.sleep()");
        }
    }

    private void randomDelay() {
        Random random = new Random();
        final int rand = random.nextInt(3) + 1;
        if (rand == 3) sleep();
    }
}
