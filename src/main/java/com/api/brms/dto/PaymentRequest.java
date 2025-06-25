package com.api.brms.dto;

public class PaymentRequest {
    private Double amount;
    private String method;

    public PaymentRequest() {}

    public PaymentRequest(Double amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
