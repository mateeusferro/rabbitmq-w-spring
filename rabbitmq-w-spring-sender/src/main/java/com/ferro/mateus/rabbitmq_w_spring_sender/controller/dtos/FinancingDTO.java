package com.ferro.mateus.rabbitmq_w_spring_sender.controller.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record FinancingDTO(String SSN, Integer installmentQuantity,
                           LocalDate dueDate, String paymentType,
                           BigDecimal grossTotal, BigDecimal interestRate) {
}
