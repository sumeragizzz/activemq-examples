package com.example.examples.controller;

import com.example.examples.constant.Operation;
import com.example.examples.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class ProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    private final ProducerService service;

    public ProducerController(ProducerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> produce(@RequestParam Operation operation) throws JsonProcessingException {
        String queueName = "testQueue01";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String message = mapper.writeValueAsString(Map.of("operation", operation, "timestamp", LocalDateTime.now()));

        switch (operation) {
            case JmsTemplate -> service.produceByJmsTemplate(queueName, message);
            case ProducerTemplate -> service.produceByProducerTemplate(queueName, message);
            default -> throw new IllegalArgumentException();
        }

        return Map.of("reason", HttpStatus.CREATED.getReasonPhrase());
    }

}
