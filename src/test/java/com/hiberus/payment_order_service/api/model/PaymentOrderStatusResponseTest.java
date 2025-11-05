package com.hiberus.payment_order_service.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderStatusResponseTest {

    @Test
    @DisplayName("Debe crear correctamente un PaymentOrderStatusResponse con el constructor requerido")
    void shouldCreateWithRequiredConstructor() {
        OffsetDateTime now = OffsetDateTime.now();
        PaymentOrderStatusResponse response =
                new PaymentOrderStatusResponse("1", "COMPLETED", now);

        assertThat(response.getPaymentOrderId()).isEqualTo("1");
        assertThat(response.getStatus()).isEqualTo("COMPLETED");
        assertThat(response.getLastUpdate()).isEqualTo(now);
    }

    @Test
    @DisplayName("Debe permitir settear y obtener propiedades correctamente")
    void shouldSetAndGetProperties() {
        OffsetDateTime now = OffsetDateTime.now();

        PaymentOrderStatusResponse response = new PaymentOrderStatusResponse()
                .paymentOrderId("2")
                .status("PROCESSING")
                .lastUpdate(now);

        assertThat(response.getPaymentOrderId()).isEqualTo("2");
        assertThat(response.getStatus()).isEqualTo("PROCESSING");
        assertThat(response.getLastUpdate()).isEqualTo(now);

        // Cambiar valores por setters
        OffsetDateTime later = now.plusHours(2);
        response.setStatus("EXECUTED");
        response.setLastUpdate(later);

        assertThat(response.getStatus()).isEqualTo("EXECUTED");
        assertThat(response.getLastUpdate()).isEqualTo(later);
    }

    @Test
    @DisplayName("Debe implementar equals y hashCode correctamente")
    void shouldImplementEqualsAndHashCode() {
        OffsetDateTime now = OffsetDateTime.now();

        PaymentOrderStatusResponse r1 = new PaymentOrderStatusResponse("1", "CREATED", now);
        PaymentOrderStatusResponse r2 = new PaymentOrderStatusResponse("1", "CREATED", now);
        PaymentOrderStatusResponse r3 = new PaymentOrderStatusResponse("2", "FAILED", now);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    @DisplayName("Debe generar un toString() legible y no nulo")
    void shouldGenerateReadableToString() {
        OffsetDateTime now = OffsetDateTime.now();
        PaymentOrderStatusResponse response =
                new PaymentOrderStatusResponse("123", "ACCEPTED", now);

        String toString = response.toString();

        assertThat(toString).isNotNull();
        assertThat(toString).contains("paymentOrderId: 123");
        assertThat(toString).contains("status: ACCEPTED");
        assertThat(toString).contains("lastUpdate");
    }
}