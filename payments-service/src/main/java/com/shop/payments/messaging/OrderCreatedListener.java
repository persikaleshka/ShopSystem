package com.shop.payments.messaging;

import com.shop.payments.service.PaymentService;
import com.shop.shared.dto.OrderCreatedEvent;
import com.shop.shared.dto.PaymentResultEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final PaymentService paymentService;
    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    public OrderCreatedListener(PaymentService paymentService, KafkaTemplate<String, PaymentResultEvent> kafkaTemplate) {
        this.paymentService = paymentService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-created", groupId = "payments")
    public void handle(OrderCreatedEvent event) {
        boolean success = paymentService.tryWithdraw(event.getUserId(), event.getAmount());

        System.out.println("Payment for order " + event.getOrderId() +
                (success ? " succeeded" : " failed") +
                " (userId=" + event.getUserId() + ", amount=" + event.getAmount() + ")");

        PaymentResultEvent result = new PaymentResultEvent(
                event.getOrderId(),
                event.getUserId(),
                event.getAmount(),
                success
        );

        kafkaTemplate.send("payment-result", result);
    }
}
