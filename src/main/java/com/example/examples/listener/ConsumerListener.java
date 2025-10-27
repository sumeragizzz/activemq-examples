package com.example.examples.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerListener.class);

    @JmsListener(destination = "testQueue01")
    public void consume(String message) {
        LOGGER.info("consumed message: {}", message);
    }

}
