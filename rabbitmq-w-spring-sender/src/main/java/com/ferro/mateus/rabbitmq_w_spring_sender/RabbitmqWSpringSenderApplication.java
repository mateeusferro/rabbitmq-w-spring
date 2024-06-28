package com.ferro.mateus.rabbitmq_w_spring_sender;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitmqWSpringSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqWSpringSenderApplication.class, args);
	}

}
