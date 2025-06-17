package com.shop.orders.outbox;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "outbox")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateType; 
    private String aggregateId;   
    private String eventType;    

    @Lob
    private String payload;       

    private Instant createdAt = Instant.now();

    private boolean sent = false;

    public OutboxEvent() {}

    public OutboxEvent(String aggregateType, String aggregateId, String eventType, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public Long getId() { return id; }
    public String getAggregateType() { return aggregateType; }
    public String getAggregateId() { return aggregateId; }
    public String getEventType() { return eventType; }
    public String getPayload() { return payload; }
    public Instant getCreatedAt() { return createdAt; }
    public boolean isSent() { return sent; }

    public void setSent(boolean sent) { this.sent = sent; }
}
