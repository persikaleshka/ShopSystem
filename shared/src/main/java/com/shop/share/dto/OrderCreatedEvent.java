package com.shop.share.dto;

public class OrderCreatedEvent {
    private Long orderId;
    private Long userId;
    private Double amount;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId, Long userId, Double amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
