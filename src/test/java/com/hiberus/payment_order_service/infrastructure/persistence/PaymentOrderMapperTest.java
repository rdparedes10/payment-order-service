package com.hiberus.payment_order_service.infrastructure.persistence;

import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.model.PaymentOrderStatus;
import com.hiberus.payment_order_service.infrastructure.persistence.entity.PaymentOrderEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderMapperTest {

    @Test
    @DisplayName("Debe mapear correctamente un PaymentOrder (dominio) a PaymentOrderEntity")
    void shouldMapDomainToEntity() {
        // given
        PaymentOrder domain = PaymentOrder.builder()
                .id(new PaymentOrderId(1L))
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 11, 5))
                .status(PaymentOrderStatus.ACCEPTED)
                .build();

        // when
        PaymentOrderEntity entity = PaymentOrderMapper.toEntity(domain);

        // then
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getExternalId()).isEqualTo("EXT-123");
        assertThat(entity.getDebtorIban()).isEqualTo("EC12DEBTOR");
        assertThat(entity.getCreditorIban()).isEqualTo("EC98CREDITOR");
        assertThat(entity.getAmount()).isEqualTo(150.75);
        assertThat(entity.getCurrency()).isEqualTo("USD");
        assertThat(entity.getRemittanceInfo()).isEqualTo("Factura 001-123");
        assertThat(entity.getRequestedExecutionDate()).isEqualTo(LocalDate.of(2025, 11, 5));
        assertThat(entity.getStatus()).isEqualTo("ACCEPTED");
    }

    @Test
    @DisplayName("Debe mapear correctamente un PaymentOrderEntity a PaymentOrder (dominio)")
    void shouldMapEntityToDomain() {
        // given
        PaymentOrderEntity entity = PaymentOrderEntity.builder()
                .id(1L)
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 11, 5))
                .status("ACCEPTED")
                .build();

        // when
        PaymentOrder domain = PaymentOrderMapper.toDomain(entity);

        // then
        assertThat(domain).isNotNull();
        assertThat(domain.getId().getValue()).isEqualTo(1L);
        assertThat(domain.getExternalId()).isEqualTo("EXT-123");
        assertThat(domain.getDebtorIban()).isEqualTo("EC12DEBTOR");
        assertThat(domain.getCreditorIban()).isEqualTo("EC98CREDITOR");
        assertThat(domain.getAmount()).isEqualTo(150.75);
        assertThat(domain.getCurrency()).isEqualTo("USD");
        assertThat(domain.getRemittanceInfo()).isEqualTo("Factura 001-123");
        assertThat(domain.getRequestedExecutionDate()).isEqualTo(LocalDate.of(2025, 11, 5));
        assertThat(domain.getStatus()).isEqualTo(PaymentOrderStatus.ACCEPTED);
    }

    @Test
    @DisplayName("Debe retornar null cuando el dominio o la entidad son null")
    void shouldReturnNullWhenInputIsNull() {
        assertThat(PaymentOrderMapper.toEntity(null)).isNull();
        assertThat(PaymentOrderMapper.toDomain(null)).isNull();
    }
}