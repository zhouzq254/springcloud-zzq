package com.scnu.zzq.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMain.class,args);
    }
}
