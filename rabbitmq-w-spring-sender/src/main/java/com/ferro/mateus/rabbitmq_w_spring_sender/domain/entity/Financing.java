package com.ferro.mateus.rabbitmq_w_spring_sender.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document("financing")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Financing {

    @MongoId
    private String id;

    @Indexed(name = "SSN")
    private String SSN;

    private Integer installmentQuantity;

    private LocalDate dueDate;

    // it can be an ENUM, but at this moment i will keep it simple
    private String paymentType;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal grossTotal;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal interestRate;
}
