package com.xieyv.lc4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Lc4jMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Lc4jMainApplication.class, args);
    }
}
