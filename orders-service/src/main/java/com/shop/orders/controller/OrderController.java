package com.shop.orders.controller;

import com.shop.orders.model.Order;
import com.shop.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order createOrder(@RequestParam Long userId, @RequestParam Double amount) {
        return service.createOrder(userId, amount);
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam Long userId) {
        return service.listOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }
}
