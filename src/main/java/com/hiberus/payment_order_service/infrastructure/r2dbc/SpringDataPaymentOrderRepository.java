package com.hiberus.payment_order_service.infrastructure.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPaymentOrderRepository extends ReactiveCrudRepository<PaymentOrderEntity, Long> {
}
