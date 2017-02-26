package com.example.kitchenhand.rxjava2;

import io.reactivex.plugins.RxJavaPlugins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Using rxjava's Schedulers.io() to make concurrent http requests means those requests happen on a different thread.
 * For zipkin to follow the trace we need to copy the state from between the tomcat request thread and the rxjava thread.
 */
@Configuration
public class SpanPropagationConfig {

    @Autowired
    private Tracer tracer;

    @PostConstruct
    public void applyHack() {
        // seems spring cloud sleuth doesn't support rxjava2.x
        RxJavaPlugins.setScheduleHandler(runnable -> {
            // cribbed from org.springframework.cloud.sleuth.instrument.rxjava.SleuthRxJavaSchedulersHook.TraceAction.call()
            Span currentSpan = tracer.getCurrentSpan();
            if (currentSpan != null) {
                return () -> {
                    tracer.continueSpan(currentSpan);
                    try {
                        runnable.run();
                    } finally {
                        tracer.detach(tracer.getCurrentSpan());
                    }
                };
            } else {
                return runnable;
            }
        });
    }

}
