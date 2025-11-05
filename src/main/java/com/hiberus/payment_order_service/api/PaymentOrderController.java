package com.hiberus.payment_order_service.api;

import com.hiberus.payment_order_service.api.model.PaymentOrderRequest;
import com.hiberus.payment_order_service.api.model.PaymentOrderResponse;
import com.hiberus.payment_order_service.api.model.PaymentOrderStatusResponse;
import com.hiberus.payment_order_service.application.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment-initiation/payment-orders")
@RequiredArgsConstructor
public class PaymentOrderController {

    private final PaymentOrderService service;

    @PostMapping
    public Mono<ResponseEntity<PaymentOrderResponse>> createPaymentOrder(
            @RequestBody PaymentOrderRequest request) {
        return service.createOrder(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PaymentOrderResponse>> getPaymentOrder(@PathVariable String id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/status")
    public Mono<ResponseEntity<PaymentOrderStatusResponse>> getPaymentOrderStatus(@PathVariable String id) {
        return service.getOrderStatus(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
