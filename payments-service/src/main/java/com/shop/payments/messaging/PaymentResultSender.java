package com.shop.payments.messaging;

import com.shop.share.dto.PaymentResultEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultSender {

    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    public PaymentResultSender(KafkaTemplate<String, PaymentResultEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Long orderId, boolean success) {
        PaymentResultEvent event = new PaymentResultEvent(orderId, success);
        kafkaTemplate.send("payment-result", event);
        System.out.println("Sent payment result: " + event);
    }
}
