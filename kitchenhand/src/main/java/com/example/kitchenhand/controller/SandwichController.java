package com.example.kitchenhand.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sandwiches")
public class SandwichController {

    private static final Logger LOG = LoggerFactory.getLogger(SandwichController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(produces = "application/json")
    public Map<String, Object> make() {
        LOG.info("Getting a candle to work by");
        Map candle = restTemplate.getForObject(findInstance("candlestickmaker").resolve("/candles"), Map.class);
        LOG.info("Lighting " + candle.get("id"));

        LOG.info("Collecting bread");
        Map bread = restTemplate.getForObject(findInstance("baker").resolve("/loaves"), Map.class);

        LOG.info("Collecting ham");
        Map ham = restTemplate.getForObject(findInstance("butcher").resolve("/ham?slices=2"), Map.class);

        LOG.info("Making a sandwich");
        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", ImmutableList.builder()
                        .add(bread)
                        .add(ham)
                        .build())
                .build();
    }

    private URI findInstance(String serviceId) {
        return discoveryClient.getInstances(serviceId).get(0).getUri();
    }
}
