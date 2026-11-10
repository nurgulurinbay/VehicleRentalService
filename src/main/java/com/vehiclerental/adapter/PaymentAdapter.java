package com.vehiclerental.adapter;


public class PaymentAdapter implements PaymentProcessor {
    private final ExternalPaymentGateway gateway;

    public PaymentAdapter(ExternalPaymentGateway gateway) {
        if (gateway == null) throw new IllegalArgumentException("Gateway cannot be null.");
        this.gateway = gateway;
    }

    @Override
    public boolean processPayment(double amount) {
        return gateway.makeTransaction(amount);
    }
}