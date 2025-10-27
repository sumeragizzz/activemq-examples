package com.example.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActivemqExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqExamplesApplication.class, args);
	}

}
