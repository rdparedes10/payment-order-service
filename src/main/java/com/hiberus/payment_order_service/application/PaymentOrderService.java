package com.hiberus.payment_order_service.application;

import com.hiberus.payment_order_service.api.model.PaymentOrderRequest;
import com.hiberus.payment_order_service.api.model.PaymentOrderResponse;
import com.hiberus.payment_order_service.api.model.PaymentOrderStatusResponse;
import com.hiberus.payment_order_service.domain.model.PaymentOrder;
import com.hiberus.payment_order_service.domain.model.PaymentOrderId;
import com.hiberus.payment_order_service.domain.model.PaymentOrderStatus;
import com.hiberus.payment_order_service.domain.repository.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PaymentOrderService {

    private final PaymentOrderRepository repository;

    @Transactional
    public Mono<PaymentOrderResponse> createOrder(PaymentOrderRequest request) {
        PaymentOrder order = PaymentOrder.builder()
                .externalId(request.getExternalId())
                .debtorIban(request.getDebtorIban())
                .creditorIban(request.getCreditorIban())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .remittanceInfo(request.getRemittanceInfo())
                .requestedExecutionDate(request.getRequestedExecutionDate())
                .status(PaymentOrderStatus.ACCEPTED)
                .build();

        return repository.save(order)
                .map(saved -> new PaymentOrderResponse()
                        .paymentOrderId(saved.getId().getValue().toString())
                        .status(saved.getStatus().name()));
    }

    public Mono<PaymentOrderResponse> getOrderById(String id) {
        return repository.findById(new PaymentOrderId(Long.parseLong(id)))
                .map(order -> new PaymentOrderResponse()
                        .paymentOrderId(order.getId().getValue().toString())
                        .status(order.getStatus().name()));
    }

    public Mono<PaymentOrderStatusResponse> getOrderStatus(String id) {
        return repository.findById(new PaymentOrderId(Long.parseLong(id)))
                .map(order -> new PaymentOrderStatusResponse()
                        .paymentOrderId(order.getId().getValue().toString())
                        .status(order.getStatus().name())
                        .lastUpdate(OffsetDateTime.now()));
    }
}