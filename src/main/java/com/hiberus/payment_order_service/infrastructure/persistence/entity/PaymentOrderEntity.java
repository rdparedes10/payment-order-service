package com.hiberus.payment_order_service.infrastructure.persistence.entity;

import java.time.LocalDate;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("payment_orders")
public class PaymentOrderEntity {

    @Id
    private Long id;
    private String externalId;
    private String debtorIban;
    private String creditorIban;
    private Double amount;
    private String currency;
    private String remittanceInfo;
    private LocalDate requestedExecutionDate;
    private String status;
}
