package com.hiberus.payment_order_service.api;

import com.hiberus.payment_order_service.api.model.PaymentOrderRequest;
import com.hiberus.payment_order_service.api.model.PaymentOrderResponse;
import com.hiberus.payment_order_service.api.model.PaymentOrderStatusResponse;
import com.hiberus.payment_order_service.application.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentOrderControllerTest {

    @Mock
    private PaymentOrderService service;

    @InjectMocks
    private PaymentOrderController controller;

    private PaymentOrderRequest request;
    private PaymentOrderResponse response;
    private PaymentOrderStatusResponse statusResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new PaymentOrderRequest()
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 11, 5));

        response = new PaymentOrderResponse("1", "ACCEPTED");

        statusResponse = new PaymentOrderStatusResponse("1", "ACCEPTED", OffsetDateTime.now());
    }

    @Test
    @DisplayName("Debe crear una orden de pago correctamente (POST /payment-orders)")
    void shouldCreatePaymentOrder() {
        when(service.createOrder(any(PaymentOrderRequest.class)))
                .thenReturn(Mono.just(response));

        StepVerifier.create(controller.createPaymentOrder(request))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
                    assertThat(entity.getBody()).isNotNull();
                    assertThat(entity.getBody().getPaymentOrderId()).isEqualTo("1");
                    assertThat(entity.getBody().getStatus()).isEqualTo("ACCEPTED");
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar una orden de pago existente (GET /payment-orders/{id})")
    void shouldReturnPaymentOrderById() {
        when(service.getOrderById("1")).thenReturn(Mono.just(response));

        StepVerifier.create(controller.getPaymentOrder("1"))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
                    assertThat(entity.getBody()).isNotNull();
                    assertThat(entity.getBody().getPaymentOrderId()).isEqualTo("1");
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar 404 cuando la orden no existe (GET /payment-orders/{id})")
    void shouldReturnNotFoundWhenOrderNotExists() {
        when(service.getOrderById("99")).thenReturn(Mono.empty());

        StepVerifier.create(controller.getPaymentOrder("99"))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCodeValue()).isEqualTo(404);
                    assertThat(entity.getBody()).isNull();
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar el estado de una orden de pago (GET /payment-orders/{id}/status)")
    void shouldReturnOrderStatus() {
        when(service.getOrderStatus("1")).thenReturn(Mono.just(statusResponse));

        StepVerifier.create(controller.getPaymentOrderStatus("1"))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
                    assertThat(entity.getBody()).isNotNull();
                    assertThat(entity.getBody().getStatus()).isEqualTo("ACCEPTED");
                    assertThat(entity.getBody().getPaymentOrderId()).isEqualTo("1");
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar 404 cuando el estado de la orden no existe (GET /payment-orders/{id}/status)")
    void shouldReturnNotFoundWhenStatusNotExists() {
        when(service.getOrderStatus("99")).thenReturn(Mono.empty());

        StepVerifier.create(controller.getPaymentOrderStatus("99"))
                .assertNext(entity -> {
                    assertThat(entity.getStatusCodeValue()).isEqualTo(404);
                })
                .verifyComplete();
    }
}