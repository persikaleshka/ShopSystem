package com.shop.payments.controller;

import com.shop.payments.model.Account;
import com.shop.payments.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Operation(summary = "Создать аккаунт пользователя", description = "Создает платёжный аккаунт по userId")
    @PostMapping("/create")
    public Account create(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId
    ) {
        return service.createAccount(userId);
    }

    @Operation(summary = "Пополнить баланс", description = "Пополняет баланс аккаунта на заданную сумму")
    @PostMapping("/topup")
    public Account topUp(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId,
            @Parameter(description = "Сумма пополнения", required = true)
            @RequestParam(name = "amount") Double amount
    ) {
        return service.topUp(userId, amount);
    }

    @Operation(summary = "Проверить баланс", description = "Возвращает текущий баланс аккаунта пользователя")
    @GetMapping("/balance")
    public Account balance(
            @Parameter(description = "ID пользователя", required = true)
            @RequestParam(name = "userId") Long userId
    ) {
        return service.getBalance(userId);
    }
}
