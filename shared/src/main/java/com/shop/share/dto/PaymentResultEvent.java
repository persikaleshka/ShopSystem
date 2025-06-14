package com.shop.share.dto;

public class PaymentResultEvent {
    private Long orderId;
    private boolean success;

    public PaymentResultEvent() {
    }

    public PaymentResultEvent(Long orderId, boolean success) {
        this.orderId = orderId;
        this.success = success;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
                ", success=" + success +
                '}';
    }
}
