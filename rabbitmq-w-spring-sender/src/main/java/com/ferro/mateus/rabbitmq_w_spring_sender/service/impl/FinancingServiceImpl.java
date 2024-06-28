package com.ferro.mateus.rabbitmq_w_spring_sender.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ferro.mateus.rabbitmq_w_spring_sender.domain.entity.Financing;
import com.ferro.mateus.rabbitmq_w_spring_sender.domain.repository.FinancingRepository;
import com.ferro.mateus.rabbitmq_w_spring_sender.sender.QueueSender;
import com.ferro.mateus.rabbitmq_w_spring_sender.sender.dto.PaymentDTO;
import com.ferro.mateus.rabbitmq_w_spring_sender.service.FinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinancingServiceImpl implements FinancingService {

    @Autowired
    private FinancingRepository financingRepository;

    @Autowired
    private QueueSender queueSender;

    @Override
    public Page<Financing> listFinancing(Integer page, Integer size) {
        if (page < 0 || size < 0) {
            throw new RuntimeException("Page or page size must be greater than 0");
        }
        if (size > 50) {
            throw new RuntimeException("Page size must be less than 50");
        }

        Pageable pageable = PageRequest.of(page, size);
        return financingRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Financing createFinancing(Financing financing) throws JsonProcessingException {
        financingRepository.save(financing);
        queueSender.send(new PaymentDTO(financing.getId(), financing.getInstallmentQuantity(),
                financing.getDueDate(), financing.getPaymentType(),
                financing.getGrossTotal(), financing.getInterestRate()));
        return financing;
    }

    @Override
    public Financing getFinancingById(String id) {
        return financingRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Financing not found"));
    }
}
