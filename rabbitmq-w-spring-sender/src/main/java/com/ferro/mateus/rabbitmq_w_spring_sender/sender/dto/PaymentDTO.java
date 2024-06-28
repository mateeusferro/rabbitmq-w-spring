package com.ferro.mateus.rabbitmq_w_spring_sender.sender.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentDTO(String id, Integer installmentQuantity,
                         LocalDate dueDate, String paymentType,
                         BigDecimal grossTotal, BigDecimal interestRate) {
}
