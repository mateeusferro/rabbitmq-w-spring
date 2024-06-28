package com.ferro.mateus.rabbitmq_w_spring_receiver.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "payment";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public Declarable paymentQueue() {
        return new Queue(QUEUE_NAME);
    }
}
