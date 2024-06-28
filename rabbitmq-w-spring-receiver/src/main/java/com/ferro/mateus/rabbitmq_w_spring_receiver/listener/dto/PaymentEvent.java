package com.ferro.mateus.rabbitmq_w_spring_receiver.listener.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentEvent(String id, Integer installmentQuantity,
                           LocalDate dueDate, String paymentType,
                           BigDecimal grossTotal, BigDecimal interestRate) {
}
