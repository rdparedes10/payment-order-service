package com.hiberus.payment_order_service.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderIdTest {

    @Test
    @DisplayName("Debe crear un PaymentOrderId correctamente y retornar su valor")
    void shouldCreateAndReturnValue() {
        PaymentOrderId id = new PaymentOrderId(10L);

        assertThat(id).isNotNull();
        assertThat(id.getValue()).isEqualTo(10L);
    }

    @Test
    @DisplayName("Debe implementar equals y hashCode correctamente")
    void shouldImplementEqualsAndHashCode() {
        PaymentOrderId id1 = new PaymentOrderId(5L);
        PaymentOrderId id2 = new PaymentOrderId(5L);
        PaymentOrderId id3 = new PaymentOrderId(9L);

        assertThat(id1).isEqualTo(id2);
        assertThat(id1.hashCode()).isEqualTo(id2.hashCode());
        assertThat(id1).isNotEqualTo(id3);
    }

    @Test
    @DisplayName("Debe generar un toString legible con el valor del ID")
    void shouldGenerateReadableToString() {
        PaymentOrderId id = new PaymentOrderId(42L);

        String toString = id.toString();

        assertThat(toString).isNotNull();
        assertThat(toString).contains("42");
    }
}