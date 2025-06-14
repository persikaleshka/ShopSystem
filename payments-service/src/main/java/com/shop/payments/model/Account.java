package com.shop.payments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Long userId; 

    private Double balance = 0.0;

    public Account() {}

    public Account(Long userId) {
        this.userId = userId;
        this.balance = 0.0;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void addFunds(Double amount) {
        this.balance += amount;
    }

    public boolean withdraw(Double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}
