package com.example.examples.controller;

import com.example.examples.service.JmsProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class JmsProducerController {

    private final JmsProducerService service;

    public JmsProducerController(JmsProducerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> produce() {
        String queueName = "testQueue01";
        String message = "test message: " + LocalDateTime.now();
        service.produce(queueName, message);

        return Map.of("reason", HttpStatus.CREATED.getReasonPhrase());
    }

}
