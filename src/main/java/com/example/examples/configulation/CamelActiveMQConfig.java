package com.example.examples.configulation;

import org.apache.camel.component.activemq.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelActiveMQConfig {

    @Bean
    public ActiveMQComponent activemqComponent() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setBrokerURL("tcp://localhost:61616");
        component.setUsername("admin");
        component.setPassword("admin");
        return component;
    }
}
