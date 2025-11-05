package com.hiberus.payment_order_service.infrastructure.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderEntityTest {

    @Test
    @DisplayName("Debe crear correctamente un PaymentOrderEntity usando el Builder")
    void shouldBuildEntityCorrectly() {
        PaymentOrderEntity entity = PaymentOrderEntity.builder()
                .id(1L)
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 11, 5))
                .status("CREATED")
                .build();

        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getExternalId()).isEqualTo("EXT-123");
        assertThat(entity.getDebtorIban()).isEqualTo("EC12DEBTOR");
        assertThat(entity.getCreditorIban()).isEqualTo("EC98CREDITOR");
        assertThat(entity.getAmount()).isEqualTo(150.75);
        assertThat(entity.getCurrency()).isEqualTo("USD");
        assertThat(entity.getRemittanceInfo()).isEqualTo("Factura 001-123");
        assertThat(entity.getRequestedExecutionDate()).isEqualTo(LocalDate.of(2025, 11, 5));
        assertThat(entity.getStatus()).isEqualTo("CREATED");
    }

    @Test
    @DisplayName("Debe permitir modificar propiedades con setters y obtenerlas con getters")
    void shouldSetAndGetPropertiesCorrectly() {
        PaymentOrderEntity entity = new PaymentOrderEntity();

        entity.setId(2L);
        entity.setExternalId("EXT-456");
        entity.setDebtorIban("EC99DEBTOR");
        entity.setCreditorIban("EC11CREDITOR");
        entity.setAmount(200.50);
        entity.setCurrency("EUR");
        entity.setRemittanceInfo("Factura 002-456");
        entity.setRequestedExecutionDate(LocalDate.of(2025, 12, 1));
        entity.setStatus("ACCEPTED");

        assertThat(entity.getId()).isEqualTo(2L);
        assertThat(entity.getExternalId()).isEqualTo("EXT-456");
        assertThat(entity.getDebtorIban()).isEqualTo("EC99DEBTOR");
        assertThat(entity.getCreditorIban()).isEqualTo("EC11CREDITOR");
        assertThat(entity.getAmount()).isEqualTo(200.50);
        assertThat(entity.getCurrency()).isEqualTo("EUR");
        assertThat(entity.getRemittanceInfo()).isEqualTo("Factura 002-456");
        assertThat(entity.getRequestedExecutionDate()).isEqualTo(LocalDate.of(2025, 12, 1));
        assertThat(entity.getStatus()).isEqualTo("ACCEPTED");
    }

    @Test
    @DisplayName("Debe respetar equals y hashCode generados por Lombok")
    void shouldImplementEqualsAndHashCodeCorrectly() {
        PaymentOrderEntity e1 = PaymentOrderEntity.builder()
                .id(1L)
                .externalId("EXT-123")
                .build();

        PaymentOrderEntity e2 = PaymentOrderEntity.builder()
                .id(1L)
                .externalId("EXT-123")
                .build();

        assertThat(e1).isEqualTo(e2);
        assertThat(e1.hashCode()).isEqualTo(e2.hashCode());
    }

    @Test
    @DisplayName("Debe generar un toString no nulo con información de la entidad")
    void shouldGenerateToString() {
        PaymentOrderEntity entity = PaymentOrderEntity.builder()
                .id(1L)
                .externalId("EXT-123")
                .status("CREATED")
                .build();

        String toString = entity.toString();

        assertThat(toString).isNotNull();
        assertThat(toString).contains("EXT-123");
        assertThat(toString).contains("CREATED");
    }
}
