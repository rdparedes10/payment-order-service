package com.hiberus.payment_order_service.infrastructure.r2dbc;

import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.model.PaymentOrderStatus;


public final class PaymentOrderMapper {

    private PaymentOrderMapper() {
    }

    public static PaymentOrderEntity toEntity(PaymentOrder domain) {
        if (domain == null) {
            return null;
        }

        PaymentOrderEntity entity = new PaymentOrderEntity();
        // id: en dominio es PaymentOrderId, en DB es Long
        entity.setId(domain.getId() != null ? domain.getId().getValue() : null);
        entity.setExternalId(domain.getExternalId());
        entity.setDebtorIban(domain.getDebtorIban());
        entity.setCreditorIban(domain.getCreditorIban());
        // ojo: tu entity tenía Double, tu dominio puede tener BigDecimal;
        // aquí asumo BigDecimal -> Double
        if (domain.getAmount() != null) {
            entity.setAmount(domain.getAmount().doubleValue());
        }
        entity.setCurrency(domain.getCurrency());
        entity.setRemittanceInfo(domain.getRemittanceInfo());
        entity.setRequestedExecutionDate(domain.getRequestedExecutionDate());
        entity.setStatus(domain.getStatus() != null ? domain.getStatus().name() : null);

        return entity;
    }

    public static PaymentOrder toDomain(PaymentOrderEntity entity) {
        if (entity == null) {
            return null;
        }

        return PaymentOrder.builder()
                .id(entity.getId() != null ? new PaymentOrderId(entity.getId()) : null)
                .externalId(entity.getExternalId())
                .debtorIban(entity.getDebtorIban())
                .creditorIban(entity.getCreditorIban())
                .amount(entity.getAmount() != null ? entity.getAmount(): null)
                .currency(entity.getCurrency())
                .remittanceInfo(entity.getRemittanceInfo())
                .requestedExecutionDate(entity.getRequestedExecutionDate())
                .status(entity.getStatus() != null ? PaymentOrderStatus.valueOf(entity.getStatus()) : null)
                .build();
    }
}