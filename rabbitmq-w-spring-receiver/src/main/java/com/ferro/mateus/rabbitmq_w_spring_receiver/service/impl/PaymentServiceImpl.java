package com.ferro.mateus.rabbitmq_w_spring_receiver.service.impl;

import com.ferro.mateus.rabbitmq_w_spring_receiver.domain.entity.Payment;
import com.ferro.mateus.rabbitmq_w_spring_receiver.domain.repository.PaymentRepository;
import com.ferro.mateus.rabbitmq_w_spring_receiver.listener.dto.PaymentEvent;
import com.ferro.mateus.rabbitmq_w_spring_receiver.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void processPayment(PaymentEvent paymentEvent) {
        for (int i = 1; i <= paymentEvent.installmentQuantity(); i++) {
            BigDecimal installmentQuantity = BigDecimal.valueOf(
                    paymentEvent.installmentQuantity());
            BigDecimal monthGrossTotal = paymentEvent.grossTotal().divide(
                    installmentQuantity,2, RoundingMode.CEILING);
            BigDecimal totalWithInterestRate =
                    monthGrossTotal
                            .multiply(BigDecimal.ONE
                                    .add(paymentEvent.interestRate()));

            Payment payment = Payment.builder()
                    .paymentInstallmentIndex(i)
                    .paymentGrossTotal(monthGrossTotal)
                    .paymentInterestRate(paymentEvent.interestRate())
                    .paymentTotalWithInterest(totalWithInterestRate)
                    .paymentDueDate(paymentEvent.dueDate().plusMonths(i))
                    .paymentType(paymentEvent.paymentType())
                    .paymentFinancingId(paymentEvent.id())
                    .build();
            paymentRepository.save(payment);
        }
    }
}
