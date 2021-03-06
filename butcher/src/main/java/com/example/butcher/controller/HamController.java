package com.example.butcher.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/ham")
public class HamController {

    private static final Logger LOG = LoggerFactory.getLogger(HamController.class);
    private static final Random RANDOM = new Random();

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public HamController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping(produces = "application/json")
    public Map<String, Object> getSlices(@RequestParam("slices") int slices) throws InterruptedException {
        LOG.info("Getting a candle to work by");
        URI uri = discoveryClient.getInstances("candlestickmaker").get(0).getUri();
        Map candle = restTemplate.getForObject(uri.resolve("/candles"), Map.class);
        LOG.info("Lighting " + candle.get("id"));

        LOG.info("Slaughtering pig");
        Thread.sleep(RANDOM.nextInt(5) * 1000);
        LOG.info("Dead pig");

        LOG.info("Slicing pig");
        Thread.sleep(RANDOM.nextInt(2) * 1000);
        LOG.info("Ready pig");

        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", slices + " slices")
                .build();
    }
}
