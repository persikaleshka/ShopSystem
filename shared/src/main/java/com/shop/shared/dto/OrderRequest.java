package com.shop.shared.dto;

public class OrderRequest {

    private Long userId;
    private Double amount;
    private String description;

    public OrderRequest() {
    }

    public OrderRequest(Long userId, Double amount, String description) {
        this.userId = userId;
        this.amount = amount;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
