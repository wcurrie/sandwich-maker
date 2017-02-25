package com.example.butcher.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/ham")
public class HamController {

    private static final Logger LOG = LoggerFactory.getLogger(HamController.class);
    private static final Random RANDOM = new Random();

    @GetMapping(produces = "application/json")
    public Map<String, Object> getSlices(@RequestParam("slices") int slices) throws InterruptedException {
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
