package com.hiberus.payment_order_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentOrder {

    private PaymentOrderId id;

    private String externalId;

    private String debtorIban;

    private String creditorIban;

    private Double amount;

    private String currency;

    private String remittanceInfo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate requestedExecutionDate;

    private PaymentOrderStatus status;
}

