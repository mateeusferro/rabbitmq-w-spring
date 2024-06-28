package com.ferro.mateus.rabbitmq_w_spring_sender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ferro.mateus.rabbitmq_w_spring_sender.controller.dtos.APIResponse;
import com.ferro.mateus.rabbitmq_w_spring_sender.controller.dtos.FinancingDTO;
import com.ferro.mateus.rabbitmq_w_spring_sender.controller.dtos.PageResponse;
import com.ferro.mateus.rabbitmq_w_spring_sender.domain.entity.Financing;
import com.ferro.mateus.rabbitmq_w_spring_sender.service.FinancingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/financing")
public class FinancingController {

    @Autowired
    private FinancingService financingService;

    @GetMapping
    public ResponseEntity<APIResponse<Financing>> listFinancing(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Financing> financingList = financingService.listFinancing(page, size);

        return new ResponseEntity<>(
                new APIResponse<>(financingList.getContent(),
                        PageResponse.fromPage(financingList)
                ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financing> getFinancingById(@PathVariable String id) {
        return new ResponseEntity<>(financingService.getFinancingById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Financing> createFinancing(@RequestBody @Valid
                                                         FinancingDTO financingDTO)
            throws JsonProcessingException {
        Financing financing = Financing.builder()
                .SSN(financingDTO.SSN())
                .installmentQuantity(financingDTO.installmentQuantity())
                .dueDate(financingDTO.dueDate())
                .paymentType(financingDTO.paymentType())
                .grossTotal(financingDTO.grossTotal())
                .interestRate(financingDTO.interestRate())
                .build();

        return new ResponseEntity<>(financingService.createFinancing(financing),
                HttpStatus.CREATED);
    }
}
