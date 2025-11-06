package com.hiberus.payment_order_service.infrastructure.persistence.repository;

import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.model.PaymentOrderStatus;
import com.hiberus.payment_order_service.infrastructure.persistence.entity.PaymentOrderEntity;
import com.hiberus.payment_order_service.infrastructure.r2dbc.SpringDataPaymentOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentOrderRepositoryImplTest {

    @Mock
    private SpringDataPaymentOrderRepository springDataRepo;

    @InjectMocks
    private PaymentOrderRepositoryImpl repositoryImpl;

    private PaymentOrder domain;
    private PaymentOrderEntity entity;

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

        entity = PaymentOrderEntity.builder()
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
    }

    @Test
    @DisplayName("Debe guardar una orden de pago correctamente")
    void shouldSavePaymentOrder() {
        // given
        when(springDataRepo.save(any(PaymentOrderEntity.class))).thenReturn(Mono.just(entity));

        // when
        Mono<PaymentOrder> result = repositoryImpl.save(domain);

        // then
        StepVerifier.create(result)
                .expectNextMatches(saved ->
                        saved.getId().getValue().equals(1L)
                                && saved.getExternalId().equals("EXT-123")
                                && saved.getStatus() == PaymentOrderStatus.ACCEPTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe encontrar una orden de pago por ID")
    void shouldFindById() {
        // given
        when(springDataRepo.findById(1L)).thenReturn(Mono.just(entity));

        // when
        Mono<PaymentOrder> result = repositoryImpl.findById(new PaymentOrderId(1L));

        // then
        StepVerifier.create(result)
                .expectNextMatches(found ->
                        found.getId().getValue().equals(1L)
                                && found.getExternalId().equals("EXT-123")
                                && found.getStatus() == PaymentOrderStatus.ACCEPTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar Mono vacío si no se encuentra la orden de pago")
    void shouldReturnEmptyIfNotFound() {
        // given
        when(springDataRepo.findById(99L)).thenReturn(Mono.empty());

        // when
        Mono<PaymentOrder> result = repositoryImpl.findById(new PaymentOrderId(99L));

        // then
        StepVerifier.create(result)
                .verifyComplete();
    }
}
