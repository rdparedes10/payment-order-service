package com.hiberus.payment_order_service.application;

import com.hiberus.payment_order_service.api.model.PaymentOrderRequest;
import com.hiberus.payment_order_service.api.model.PaymentOrderResponse;
import com.hiberus.payment_order_service.api.model.PaymentOrderStatusResponse;
import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.model.PaymentOrderStatus;
import com.hiberus.payment_order_service.domain.repository.PaymentOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentOrderServiceTest {

    @Mock
    private PaymentOrderRepository repository;

    @InjectMocks
    private PaymentOrderService service;

    private PaymentOrder domain;
    private PaymentOrderRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        domain = PaymentOrder.builder()
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

        request = new PaymentOrderRequest()
                .externalId("EXT-123")
                .debtorIban("EC12DEBTOR")
                .creditorIban("EC98CREDITOR")
                .amount(150.75)
                .currency("USD")
                .remittanceInfo("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 11, 5));
    }

    @Test
    @DisplayName("Debe crear una orden de pago correctamente")
    void shouldCreatePaymentOrder() {
        // given
        when(repository.save(any(PaymentOrder.class))).thenReturn(Mono.just(domain));

        // when
        Mono<PaymentOrderResponse> result = service.createOrder(request);

        // then
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertThat(response.getPaymentOrderId()).isEqualTo("1");
                    assertThat(response.getStatus()).isEqualTo("ACCEPTED");
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe obtener una orden de pago por ID correctamente")
    void shouldGetOrderById() {
        // given
        when(repository.findById(any(PaymentOrderId.class))).thenReturn(Mono.just(domain));

        // when
        Mono<PaymentOrderResponse> result = service.getOrderById("1");

        // then
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertThat(response.getPaymentOrderId()).isEqualTo("1");
                    assertThat(response.getStatus()).isEqualTo("ACCEPTED");
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe obtener el estado de una orden correctamente")
    void shouldGetOrderStatus() {
        // given
        when(repository.findById(any(PaymentOrderId.class))).thenReturn(Mono.just(domain));

        // when
        Mono<PaymentOrderStatusResponse> result = service.getOrderStatus("1");

        // then
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertThat(response.getPaymentOrderId()).isEqualTo("1");
                    assertThat(response.getStatus()).isEqualTo("ACCEPTED");
                    assertThat(response.getLastUpdate()).isNotNull();
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar Mono vacío cuando no se encuentra la orden")
    void shouldReturnEmptyIfNotFound() {
        // given
        when(repository.findById(any(PaymentOrderId.class))).thenReturn(Mono.empty());

        // when
        Mono<PaymentOrderResponse> result = service.getOrderById("99");

        // then
        StepVerifier.create(result)
                .verifyComplete();
    }
}
