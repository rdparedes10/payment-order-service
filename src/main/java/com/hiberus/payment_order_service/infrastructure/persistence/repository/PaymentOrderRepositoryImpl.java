package com.hiberus.payment_order_service.infrastructure.persistence.repository;


import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.repository.PaymentOrderRepository;
import com.hiberus.payment_order_service.infrastructure.persistence.PaymentOrderMapper;
import com.hiberus.payment_order_service.infrastructure.r2dbc.SpringDataPaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public class PaymentOrderRepositoryImpl implements PaymentOrderRepository {

    private final SpringDataPaymentOrderRepository repository;

    @Override
    public Mono<PaymentOrder> save(PaymentOrder order) {
        return repository.save(PaymentOrderMapper.toEntity(order))
                .map(PaymentOrderMapper::toDomain);
    }

    @Override
    public Mono<PaymentOrder> findById(PaymentOrderId id) {
        return repository.findById(id.getValue())
                .map(PaymentOrderMapper::toDomain);
    }
}