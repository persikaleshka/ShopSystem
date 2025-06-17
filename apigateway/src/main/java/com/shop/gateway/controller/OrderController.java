package com.shop.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final WebClient webClient;

    @Autowired
    public OrderController(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://orders-service:8080").build();
    }

    @Operation(summary = "Создать заказ", description = "Создаёт заказ по ID пользователя и сумме")
    @PostMapping
    public Mono<ResponseEntity<String>> createOrder(
        @Parameter(name = "userId", description = "ID пользователя", required = true)
        @RequestParam(name = "userId") Long userId,

        @Parameter(name = "amount", description = "Сумма заказа", required = true)
        @RequestParam(name = "amount") Double amount
    ) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/orders")
                        .queryParam("userId", userId)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .toEntity(String.class);
    }

    @Operation(summary = "Получить все заказы", description = "Получить список заказов по ID пользователя")
    @GetMapping
    public Mono<ResponseEntity<String>> getOrders(
        @Parameter(name = "userId", description = "ID пользователя", required = true)
        @RequestParam(name = "userId") Long userId
    ) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/orders")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .toEntity(String.class);
    }

    @Operation(summary = "Получить заказ по ID", description = "Получить конкретный заказ по ID")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getOrderById(
        @Parameter(name = "id", description = "ID заказа", required = true)
        @PathVariable(name = "id") Long id
    ) {
        return webClient.get()
                .uri("/api/orders/{id}", id)
                .retrieve()
                .toEntity(String.class);
    }
}
