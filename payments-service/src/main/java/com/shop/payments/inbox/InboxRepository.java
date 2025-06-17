package com.shop.payments.inbox;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InboxRepository extends JpaRepository<InboxEvent, Long> {
    Optional<InboxEvent> findByEventId(String eventId);
}
