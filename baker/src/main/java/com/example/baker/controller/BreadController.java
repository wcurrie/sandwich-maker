package com.example.baker.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/loaves")
public class BreadController {

    private static final Logger LOG = LoggerFactory.getLogger(BreadController.class);
    private static final Random RANDOM = new Random();

    private final RestTemplate restTemplate;

    @Autowired
    public BreadController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(produces = "application/json")
    public Map<String, Object> make() throws InterruptedException {
        LOG.info("Getting a candle to work by");
        Map candle = restTemplate.getForObject("http://localhost:8083/candles", Map.class);
        LOG.info("Lighting " + candle.get("id"));
        LOG.info("Baking loaf");
        Thread.sleep(RANDOM.nextInt(5) * 1000);
        LOG.info("Baking done");
        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", "28 wholegrain slices")
                .build();
    }
}
