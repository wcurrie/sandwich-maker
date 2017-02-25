package com.example.baker.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/loaves")
public class BreadController {

    private static final Logger LOG = LoggerFactory.getLogger(BreadController.class);
    private static final Random RANDOM = new Random();

    @GetMapping(produces = "application/json")
    public Map<String, Object> make() throws InterruptedException {
        LOG.info("Baking loaf");
        Thread.sleep(RANDOM.nextInt(5) * 1000);
        LOG.info("Baking done");
        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", "28 wholegrain slices")
                .build();
    }
}
