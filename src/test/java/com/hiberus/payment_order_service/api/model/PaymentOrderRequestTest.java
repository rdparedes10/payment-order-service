package com.hiberus.payment_order_service.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderRequestTest {

    @Test
    @DisplayName("Debe crear correctamente un PaymentOrderRequest con el constructor requerido")
    void shouldCreateWithRequiredConstructor() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        PaymentOrderRequest request = new PaymentOrderRequest(
                "EXT-123",
                "EC12DEBTOR",
                "EC98CREDITOR",
                150.75,
                "USD",
                date
        );

        assertThat(request.getExternalId()).isEqualTo("EXT-123");
        assertThat(request.getDebtorIban()).isEqualTo("EC12DEBTOR");
        assertThat(request.getCreditorIban()).isEqualTo("EC98CREDITOR");
        assertThat(request.getAmount()).isEqualTo(150.75);
        assertThat(request.getCurrency()).isEqualTo("USD");
        assertThat(request.getRequestedExecutionDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("Debe permitir settear y obtener todas las propiedades correctamente")
    void shouldSetAndGetAllProperties() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        PaymentOrderRequest request = new PaymentOrderRequest()
                .externalId("EXT-999")
                .debtorIban("EC11DEBTOR")
                .creditorIban("EC22CREDITOR")
                .amount(200.50)
                .currency("EUR")
                .remittanceInfo("Pago servicio eléctrico")
                .requestedExecutionDate(date);

        assertThat(request.getExternalId()).isEqualTo("EXT-999");
        assertThat(request.getDebtorIban()).isEqualTo("EC11DEBTOR");
        assertThat(request.getCreditorIban()).isEqualTo("EC22CREDITOR");
        assertThat(request.getAmount()).isEqualTo(200.50);
        assertThat(request.getCurrency()).isEqualTo("EUR");
        assertThat(request.getRemittanceInfo()).isEqualTo("Pago servicio eléctrico");
        assertThat(request.getRequestedExecutionDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("Debe implementar equals y hashCode correctamente")
    void shouldImplementEqualsAndHashCode() {
        LocalDate date = LocalDate.of(2025, 11, 5);

        PaymentOrderRequest r1 = new PaymentOrderRequest(
                "EXT-1", "IBAN1", "IBAN2", 10.0, "USD", date);
        PaymentOrderRequest r2 = new PaymentOrderRequest(
                "EXT-1", "IBAN1", "IBAN2", 10.0, "USD", date);
        PaymentOrderRequest r3 = new PaymentOrderRequest(
                "EXT-2", "IBAN3", "IBAN4", 15.0, "EUR", date);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    @DisplayName("Debe generar un toString() legible y no nulo")
    void shouldGenerateReadableToString() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        PaymentOrderRequest request = new PaymentOrderRequest(
                "EXT-123", "EC12DEBTOR", "EC98CREDITOR", 150.75, "USD", date);
        request.setRemittanceInfo("Factura 001-123");

        String toString = request.toString();

        assertThat(toString).contains("EXT-123");
        assertThat(toString).contains("EC12DEBTOR");
        assertThat(toString).contains("EC98CREDITOR");
        assertThat(toString).contains("USD");
        assertThat(toString).contains("Factura 001-123");
    }
}