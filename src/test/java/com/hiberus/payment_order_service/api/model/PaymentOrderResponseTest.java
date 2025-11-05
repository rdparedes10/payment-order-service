package com.hiberus.payment_order_service.api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderResponseTest {

    @Test
    @DisplayName("Debe crear correctamente un PaymentOrderResponse con el constructor requerido")
    void shouldCreateWithRequiredConstructor() {
        PaymentOrderResponse response = new PaymentOrderResponse("1", "ACCEPTED");

        assertThat(response.getPaymentOrderId()).isEqualTo("1");
        assertThat(response.getStatus()).isEqualTo("ACCEPTED");
    }

    @Test
    @DisplayName("Debe permitir settear y obtener propiedades correctamente")
    void shouldSetAndGetProperties() {
        PaymentOrderResponse response = new PaymentOrderResponse()
                .paymentOrderId("2")
                .status("CREATED");

        assertThat(response.getPaymentOrderId()).isEqualTo("2");
        assertThat(response.getStatus()).isEqualTo("CREATED");

        response.setStatus("EXECUTED");
        assertThat(response.getStatus()).isEqualTo("EXECUTED");
    }

    @Test
    @DisplayName("Debe implementar equals y hashCode correctamente")
    void shouldImplementEqualsAndHashCode() {
        PaymentOrderResponse r1 = new PaymentOrderResponse("10", "CREATED");
        PaymentOrderResponse r2 = new PaymentOrderResponse("10", "CREATED");
        PaymentOrderResponse r3 = new PaymentOrderResponse("11", "REJECTED");

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    @DisplayName("Debe generar un toString() legible y no nulo")
    void shouldGenerateReadableToString() {
        PaymentOrderResponse response = new PaymentOrderResponse("123", "ACCEPTED");

        String toString = response.toString();

        assertThat(toString).isNotNull();
        assertThat(toString).contains("paymentOrderId: 123");
        assertThat(toString).contains("status: ACCEPTED");
    }
}