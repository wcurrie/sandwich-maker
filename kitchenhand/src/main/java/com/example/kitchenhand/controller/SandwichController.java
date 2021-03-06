package com.example.kitchenhand.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sandwiches")
public class SandwichController {

    private static final Logger LOG = LoggerFactory.getLogger(SandwichController.class);

    private final RestTemplate restTemplate;

    @Autowired
    public SandwichController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(produces = "application/json")
    public Map<String, Object> make() {
        LOG.info("Getting a candle to work by");
        Map candle = restTemplate.getForObject("http://candlestickmaker/candles", Map.class);
        LOG.info("Lighting " + candle.get("id"));

        LOG.info("Collecting bread");
        Map bread = restTemplate.getForObject("http://baker/loaves", Map.class);

        LOG.info("Collecting ham");
        Map ham = restTemplate.getForObject("http://butcher/ham?slices=2", Map.class);

        return makeSandwich(Arrays.asList(bread, ham));
    }

    @PostMapping(produces = "application/json", params = "turbo=true")
    public Map<String, Object> makeQuickly() {
        LOG.info("Getting a candle to work by");
        Map candle = restTemplate.getForObject("http://candlestickmaker/candles", Map.class);
        LOG.info("Lighting " + candle.get("id"));

        // probably a terrible use of rx-java

        LOG.info("Gathering ingredients");

        Single<Map> bread = Single.fromCallable(() -> {
            LOG.info("Collecting bread");
            return restTemplate.getForObject("http://baker/loaves", Map.class);
        }).subscribeOn(Schedulers.io());

        Single<Map> ham = Single.fromCallable(() -> {
            LOG.info("Collecting ham");
            return restTemplate.getForObject("http://butcher/ham?slices=2", Map.class);
        }).subscribeOn(Schedulers.io());

        Iterable<Map> ingredients = Single.merge(bread, ham).blockingIterable();
        return makeSandwich(ingredients);
    }

    private Map<String, Object> makeSandwich(Iterable<Map> ingredients) {
        LOG.info("Making a sandwich");
        return new ImmutableMap.Builder<String, Object>()
                .put("id", UUID.randomUUID())
                .put("contents", ImmutableList.builder()
                        .addAll(ingredients)
                        .build())
                .build();
    }

}
