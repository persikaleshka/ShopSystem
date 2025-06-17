package com.shop.payments.inbox;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "inbox", uniqueConstraints = @UniqueConstraint(columnNames = "eventId"))
public class InboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;

    private String eventType;

    private Instant receivedAt = Instant.now();

    public InboxEvent() {}

    public InboxEvent(String eventId, String eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public Long getId() { return id; }

    public String getEventId() { return eventId; }

    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventType() { return eventType; }

    public void setEventType(String eventType) { this.eventType = eventType; }

    public Instant getReceivedAt() { return receivedAt; }
}
