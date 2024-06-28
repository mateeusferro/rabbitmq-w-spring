package com.ferro.mateus.rabbitmq_w_spring_receiver.listener;

import com.ferro.mateus.rabbitmq_w_spring_receiver.listener.dto.PaymentEvent;
import com.ferro.mateus.rabbitmq_w_spring_receiver.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ferro.mateus.rabbitmq_w_spring_receiver.config.RabbitMQConfig.QUEUE_NAME;

@Component
public class PaymentListener {

    private final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(Message<PaymentEvent> message) throws Exception{
        logger.info("Received message: {}", message.getPayload());
        paymentService.processPayment(message.getPayload());
    }
}
