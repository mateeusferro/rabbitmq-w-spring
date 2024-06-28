package com.ferro.mateus.rabbitmq_w_spring_sender.domain.repository;

import com.ferro.mateus.rabbitmq_w_spring_sender.domain.entity.Financing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinancingRepository extends MongoRepository<Financing, String> {
}
