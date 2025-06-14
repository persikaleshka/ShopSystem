package com.shop.orders.messaging;

import com.shop.orders.model.Order;
import com.shop.orders.model.OrderStatus;
import com.shop.orders.repository.OrderRepository;
import com.shop.share.dto.PaymentResultEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentListener {

    private final OrderRepository orderRepository;

    public OrderPaymentListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "payment-result", groupId = "orders")
    public void handlePaymentResult(PaymentResultEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            if (event.isSuccess()) {
                order.setStatus(OrderStatus.FINISHED);
            } else {
                order.setStatus(OrderStatus.CANCELLED);
            }
            orderRepository.save(order);
            System.out.println("Updated order " + order.getId() + " to status " + order.getStatus());
        });
    }
}
