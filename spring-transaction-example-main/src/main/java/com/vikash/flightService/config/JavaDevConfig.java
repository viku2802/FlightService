package com.vikash.flightService.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class JavaDevConfig {

    @PostConstruct
    public void test() {
        System.out.println("Loaded DEV environment");
    }
}