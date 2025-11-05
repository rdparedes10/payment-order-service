package com.hiberus.payment_order_service.domain.repository;


import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import reactor.core.publisher.Mono;

public interface PaymentOrderRepository {
    Mono<PaymentOrder> save(PaymentOrder order);
    Mono<PaymentOrder> findById(PaymentOrderId id);
}