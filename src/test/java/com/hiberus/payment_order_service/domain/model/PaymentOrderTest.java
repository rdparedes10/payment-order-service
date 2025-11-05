package com.hiberus.payment_order_service.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderTest {

    @Test
    @DisplayName("Debe crear un PaymentOrder correctamente usando el Builder")
    void shouldBuildPaymentOrderCorrectly() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        PaymentOrder order = PaymentOrder.builder()
                .id(new PaymentOrderId(1L))
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(date)
                .status(PaymentOrderStatus.ACCEPTED)
                .build();

        assertThat(order.getId().getValue()).isEqualTo(1L);
        assertThat(order.getExternalId()).isEqualTo("EXT-123");
        assertThat(order.getDebtorIban()).isEqualTo("EC12DEBTOR");
        assertThat(order.getCreditorIban()).isEqualTo("EC98CREDITOR");
        assertThat(order.getAmount()).isEqualTo(150.75);
        assertThat(order.getCurrency()).isEqualTo("USD");
        assertThat(order.getRemittanceInfo()).isEqualTo("Factura 001-123");
        assertThat(order.getRequestedExecutionDate()).isEqualTo(date);
        assertThat(order.getStatus()).isEqualTo(PaymentOrderStatus.ACCEPTED);
    }

    @Test
    @DisplayName("Debe permitir modificar los valores mediante setters")
    void shouldAllowSettingValues() {
        PaymentOrder order = new PaymentOrder();
        LocalDate date = LocalDate.of(2025, 12, 1);

        order.setId(new PaymentOrderId(2L));
        order.setExternalId("EXT-999");
        order.setDebtorIban("EC22DEBTOR");
        order.setCreditorIban("EC33CREDITOR");
        order.setAmount(200.50);
        order.setCurrency("EUR");
        order.setRemittanceInfo("Pago de servicio");
        order.setRequestedExecutionDate(date);
        order.setStatus(PaymentOrderStatus.ACCEPTED);

        assertThat(order.getId().getValue()).isEqualTo(2L);
        assertThat(order.getExternalId()).isEqualTo("EXT-999");
        assertThat(order.getDebtorIban()).isEqualTo("EC22DEBTOR");
        assertThat(order.getCreditorIban()).isEqualTo("EC33CREDITOR");
        assertThat(order.getAmount()).isEqualTo(200.50);
        assertThat(order.getCurrency()).isEqualTo("EUR");
        assertThat(order.getRemittanceInfo()).isEqualTo("Pago de servicio");
        assertThat(order.getRequestedExecutionDate()).isEqualTo(date);
        assertThat(order.getStatus()).isEqualTo(PaymentOrderStatus.ACCEPTED);
    }

    @Test
    @DisplayName("Debe implementar equals y hashCode correctamente")
    void shouldImplementEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2025, 11, 5);

        PaymentOrder o1 = PaymentOrder.builder()
                .id(new PaymentOrderId(1L))
                .externalId("EXT-001")
                .requestedExecutionDate(date)
                .build();

        PaymentOrder o2 = PaymentOrder.builder()
                .id(new PaymentOrderId(1L))
                .externalId("EXT-001")
                .requestedExecutionDate(date)
                .build();

        PaymentOrder o3 = PaymentOrder.builder()
                .id(new PaymentOrderId(2L))
                .externalId("EXT-002")
                .requestedExecutionDate(date)
                .build();

        assertThat(o1).isEqualTo(o2);
        assertThat(o1.hashCode()).isEqualTo(o2.hashCode());
        assertThat(o1).isNotEqualTo(o3);
    }

    @Test
    @DisplayName("Debe generar un toString no nulo con información relevante")
    void shouldGenerateToString() {
        PaymentOrder order = PaymentOrder.builder()
                .id(new PaymentOrderId(1L))
                .externalId("EXT-123")
                .status(PaymentOrderStatus.ACCEPTED)
                .build();

        String toString = order.toString();

        assertThat(toString).isNotNull();
        assertThat(toString).contains("EXT-123");
        assertThat(toString).contains("ACCEPTED");
    }
}