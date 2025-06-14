package com.shop.orders.messaging;

import com.shop.orders.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OutboxPublisher {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OutboxPublisher(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getUserId(),
                order.getAmount()
        );
        kafkaTemplate.send("order-created", event);
    }
}
