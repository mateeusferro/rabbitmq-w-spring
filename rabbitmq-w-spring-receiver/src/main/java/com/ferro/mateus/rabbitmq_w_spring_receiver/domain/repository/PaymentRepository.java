package com.ferro.mateus.rabbitmq_w_spring_receiver.domain.repository;

import com.ferro.mateus.rabbitmq_w_spring_receiver.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
