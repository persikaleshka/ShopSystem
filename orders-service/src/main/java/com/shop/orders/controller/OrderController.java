package com.shop.orders.controller;

import com.shop.orders.model.Order;
import com.shop.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @Operation(summary = "Создать заказ", description = "Создаёт новый заказ по ID пользователя и сумме.")
    @PostMapping
    public Order createOrder(
        @RequestParam(name = "userId") Long userId,
        @RequestParam(name = "amount") Double amount
    ) {
        return service.createOrder(userId, amount);
    }

    @Operation(summary = "Получить все заказы пользователя", description = "Возвращает список всех заказов по ID пользователя.")
    @GetMapping
    public List<Order> getOrders(@RequestParam(name = "userId") Long userId) {
        return service.listOrders(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить заказ по ID", description = "Возвращает заказ по его уникальному ID.")
    public Order getOrder(
        @Parameter(description = "ID заказа", example = "42") 
        @PathVariable(name = "id") Long id
    ) {
        return service.getOrder(id);
    }
}
