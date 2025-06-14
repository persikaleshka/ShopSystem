package com.shop.orders.service;

import com.shop.orders.model.Order;
import com.shop.orders.model.OrderStatus;
import com.shop.orders.repository.OrderRepository;
import com.shop.orders.messaging.OutboxPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OutboxPublisher outboxPublisher;

    public OrderService(OrderRepository repository, OutboxPublisher outboxPublisher) {
        this.repository = repository;
        this.outboxPublisher = outboxPublisher;
    }

    public Order createOrder(Long userId, Double amount) {
        Order order = new Order();
        order.setUserId(userId);
        order.setAmount(amount);
        order.setStatus(OrderStatus.NEW);

        Order saved = repository.save(order);

        outboxPublisher.publishOrderCreated(saved);

        return saved;
    }

    public List<Order> listOrders(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Order getOrder(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
