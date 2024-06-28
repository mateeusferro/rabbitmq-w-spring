package com.ferro.mateus.rabbitmq_w_spring_sender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ferro.mateus.rabbitmq_w_spring_sender.domain.entity.Financing;
import org.springframework.data.domain.Page;

public interface FinancingService {

    Page<Financing> listFinancing(Integer page, Integer size);

    Financing createFinancing(Financing financing) throws JsonProcessingException;

    Financing getFinancingById(String id);
}
