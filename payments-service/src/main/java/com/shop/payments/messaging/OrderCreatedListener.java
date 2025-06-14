package com.shop.payments.messaging;

import com.shop.payments.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final PaymentService paymentService;

    public OrderCreatedListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "order-created", groupId = "payments")
    public void handle(OrderCreatedEvent event) {
        boolean success = paymentService.tryWithdraw(event.getUserId(), event.getAmount());
        System.out.println("Payment for order " + event.getOrderId() +
                (success ? " succeeded" : " failed") +
                " (userId=" + event.getUserId() + ", amount=" + event.getAmount() + ")");
    }
}
