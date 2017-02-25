package com.example.candlestickmaker.controller;

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
@RequestMapping("/candles")
public class CandleController {

    private static final Logger LOG = LoggerFactory.getLogger(CandleController.class);
    private static final Random RANDOM = new Random();

    @GetMapping(produces = "application/json")
    public Map<String, Object> getCandle() throws InterruptedException {
        LOG.info("Making candle");
        Thread.sleep(RANDOM.nextInt(1000));
        LOG.info("Candle made");
        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", "A candle")
                .build();
    }
}
