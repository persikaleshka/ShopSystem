package com.shop.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final WebClient.Builder webClientBuilder;

    public PaymentController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Operation(summary = "Создать аккаунт пользователя", description = "Создает платёжный аккаунт по userId")
    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createAccount(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId) {
        return webClientBuilder.build()
                .post()
                .uri("http://payments-service:8080/api/payments/create?userId=" + userId)
                .retrieve()
                .toEntity(String.class);
    }

    @Operation(summary = "Пополнить баланс", description = "Пополняет баланс аккаунта на заданную сумму")
    @PostMapping("/topup")
    public Mono<ResponseEntity<String>> topUp(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId,
            @Parameter(description = "Сумма пополнения", required = true)
            @RequestParam(name = "amount") Double amount) {
        return webClientBuilder.build()
                .post()
                .uri("http://payments-service:8080/api/payments/topup?userId=" + userId + "&amount=" + amount)
                .retrieve()
                .toEntity(String.class);
    }

    @Operation(summary = "Получить баланс", description = "Возвращает текущий баланс пользователя")
    @GetMapping("/balance")
    public Mono<ResponseEntity<String>> balance(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId) {
        return webClientBuilder.build()
                .get()
                .uri("http://payments-service:8080/api/payments/balance?userId=" + userId)
                .retrieve()
                .toEntity(String.class);
    }
}
