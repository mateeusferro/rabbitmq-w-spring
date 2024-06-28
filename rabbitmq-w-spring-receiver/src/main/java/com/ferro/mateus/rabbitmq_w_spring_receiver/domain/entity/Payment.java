package com.ferro.mateus.rabbitmq_w_spring_receiver.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID id;

    @Column(name = "payment_installment_index")
    private Integer paymentInstallmentIndex;

    @Column(name = "payment_gross_total")
    private BigDecimal paymentGrossTotal;

    @Temporal(TemporalType.DATE)
    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_interest_rate")
    private BigDecimal paymentInterestRate;

    @Column(name = "payment_financing_id")
    private String paymentFinancingId;

    @Column(name = "payment_total_with_interest")
    private BigDecimal paymentTotalWithInterest;
}
