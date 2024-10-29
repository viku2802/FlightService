package com.vikash.flightService;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "asdf")// locations= "C:/Users/Admin/Documents/application.properties")
public class ASDF {
    private String name;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}