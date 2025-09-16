package com.example.examples.service;

import jakarta.jms.TextMessage;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    private final JmsTemplate jmsTemplate;

    private final ProducerTemplate producerTemplate;

    public ProducerService(JmsTemplate jmsTemplate, ProducerTemplate producerTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.producerTemplate = producerTemplate;
    }

    public void produceByJmsTemplate(String queueName, String message) {
        jmsTemplate.send(queueName, session -> {
            TextMessage textMessage = session.createTextMessage(message);
            LOGGER.info("JMSDeliveryMode: {}", textMessage.getJMSDeliveryMode());
            return textMessage;
        });
    }

    public void produceByProducerTemplate(String queueName, String message) {
        producerTemplate.sendBody("activemq:%s".formatted(queueName), message);
    }

}
