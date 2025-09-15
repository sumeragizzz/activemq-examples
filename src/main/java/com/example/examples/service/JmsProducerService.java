package com.example.examples.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducerService {

    private final JmsTemplate jmsTemplate;

    public JmsProducerService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void produce(String queueName, String message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

}
