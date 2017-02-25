package com.example.candlestickmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CandleStickMaker {

    public static void main(String[] args) {
        SpringApplication.run(CandleStickMaker.class, args);
    }
}
