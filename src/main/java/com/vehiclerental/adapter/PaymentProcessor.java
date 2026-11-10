package com.vehiclerental.adapter;

/**
 * Target interface expected by the rental application.
 */
public interface PaymentProcessor {
    boolean processPayment(double amount);
}