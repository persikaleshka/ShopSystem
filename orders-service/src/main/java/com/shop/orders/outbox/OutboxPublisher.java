package com.shop.orders.outbox;

import com.shop.shared.dto.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OutboxPublisher(OutboxRepository outboxRepository,
                           KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 5000)
    public void publishEvents() {
        List<OutboxEvent> events = outboxRepository.findAllBySentFalse();

        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send("order-created", event.getAggregateId(), event.getPayload());
                event.setSent(true);
                outboxRepository.save(event);
                System.out.println("Outbox event sent: " + event.getPayload());
            } catch (Exception e) {
                System.err.println("Failed to send outbox event: " + e.getMessage());
                // можно добавить retry логику или dead-letter
            }
        }
    }
}
