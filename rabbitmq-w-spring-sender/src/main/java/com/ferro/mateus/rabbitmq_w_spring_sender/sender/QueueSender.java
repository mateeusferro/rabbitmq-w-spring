package com.ferro.mateus.rabbitmq_w_spring_sender.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferro.mateus.rabbitmq_w_spring_sender.sender.dto.PaymentDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(PaymentDTO payment) throws JsonProcessingException {
        String stringFinancing = objectMapper.writeValueAsString(payment);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(stringFinancing.getBytes(), messageProperties);
        rabbitTemplate.send(queue.getName(), message);
    }
}
