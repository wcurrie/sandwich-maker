package com.example.butcher.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/ham")
public class HamController {

    private static final Logger LOG = LoggerFactory.getLogger(HamController.class);
    private static final Random RANDOM = new Random();

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(produces = "application/json")
    public Map<String, Object> getSlices(@RequestParam("slices") int slices) throws InterruptedException {
        LOG.info("Getting a candle to work by");
        Map candle = restTemplate.getForObject("http://localhost:8083/candles", Map.class);
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
