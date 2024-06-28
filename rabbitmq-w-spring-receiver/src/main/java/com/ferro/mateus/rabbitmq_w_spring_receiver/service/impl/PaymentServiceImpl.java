package com.ferro.mateus.rabbitmq_w_spring_receiver.service.impl;

import com.ferro.mateus.rabbitmq_w_spring_receiver.domain.entity.Payment;
import com.ferro.mateus.rabbitmq_w_spring_receiver.domain.repository.PaymentRepository;
import com.ferro.mateus.rabbitmq_w_spring_receiver.listener.dto.PaymentEvent;
import com.ferro.mateus.rabbitmq_w_spring_receiver.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void processPayment(PaymentEvent paymentEvent) {
        for (int i = 1; i <= paymentEvent.installmentQuantity(); i++) {
            Payment payment = Payment.builder()
                    .paymentInstallmentIndex(i)
                    .paymentGrossTotal(paymentEvent.grossTotal())
                    .paymentInterestRate(paymentEvent.interestRate())
                    .paymentTotalWithInterest(paymentEvent.grossTotal())
                    .paymentDueDate(paymentEvent.dueDate().plusMonths(i))
                    .paymentType(paymentEvent.paymentType())
                    .paymentFinancingId(paymentEvent.id())
                    .build();
            paymentRepository.save(payment);
        }
    }
}
