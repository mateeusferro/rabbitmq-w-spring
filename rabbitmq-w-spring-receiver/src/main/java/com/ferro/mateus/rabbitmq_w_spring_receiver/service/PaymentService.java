package com.ferro.mateus.rabbitmq_w_spring_receiver.service;

import com.ferro.mateus.rabbitmq_w_spring_receiver.listener.dto.PaymentEvent;

public interface PaymentService {

    void processPayment(PaymentEvent paymentEvent);
}
