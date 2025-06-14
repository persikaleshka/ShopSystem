package com.shop.payments.controller;

import com.shop.payments.model.Account;
import com.shop.payments.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Account create(@RequestParam Long userId) {
        return service.createAccount(userId);
    }

    @PostMapping("/topup")
    public Account topUp(@RequestParam Long userId, @RequestParam Double amount) {
        return service.topUp(userId, amount);
    }

    @GetMapping("/balance")
    public Account balance(@RequestParam Long userId) {
        return service.getBalance(userId);
    }
}
