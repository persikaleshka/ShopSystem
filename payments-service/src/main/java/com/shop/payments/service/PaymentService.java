package com.shop.payments.service;

import com.shop.payments.model.Account;
import com.shop.payments.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final AccountRepository repository;

    public PaymentService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Long userId) {
        if (repository.existsById(userId)) {
            throw new RuntimeException("Account already exists");
        }
        return repository.save(new Account(userId));
    }

    public Account topUp(Long userId, Double amount) {
        Account acc = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        acc.addFunds(amount);
        return repository.save(acc);
    }

    public Account getBalance(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public boolean tryWithdraw(Long userId, Double amount) {
        Optional<Account> accOpt = repository.findById(userId);
        if (accOpt.isEmpty()) return false;
        Account acc = accOpt.get();
        boolean success = acc.withdraw(amount);
        if (success) repository.save(acc);
        return success;
    }
}
