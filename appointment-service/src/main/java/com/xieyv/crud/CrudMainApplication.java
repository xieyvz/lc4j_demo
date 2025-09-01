package com.xieyv.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CrudMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudMainApplication.class, args);
    }
}
