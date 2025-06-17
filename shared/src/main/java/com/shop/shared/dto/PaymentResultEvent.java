package com.shop.shared.dto;

public class PaymentResultEvent {
    private Long orderId;
    private Long userId;
    private Double amount;
    private boolean success;

    public PaymentResultEvent() {
    }

    public PaymentResultEvent(Long orderId, Long userId, Double amount, boolean success) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.success = success;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PaymentResultEvent{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", success=" + success +
                '}';
    }
}
