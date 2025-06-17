package com.shop.payments.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.shop.shared.dto.PaymentResultEvent;

@Component
public class PaymentResultSender {

    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    public PaymentResultSender(KafkaTemplate<String, PaymentResultEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Long orderId, Long userId, Double amount, boolean success) {
        PaymentResultEvent event = new PaymentResultEvent(orderId, userId, amount, success);
        kafkaTemplate.send("payment-result", event);
        System.out.println("Sent payment result: " + event);
    }
}
