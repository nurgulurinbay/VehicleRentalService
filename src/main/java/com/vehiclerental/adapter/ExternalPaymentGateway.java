package com.vehiclerental.adapter;

/**
 * Simulated third-party gateway with a different API.
 * This intentionally does not implement PaymentProcessor.
 */
public class ExternalPaymentGateway {
    public boolean makeTransaction(double amountInKzt) {
        if (amountInKzt <= 0) {
            System.out.println("Gateway rejected transaction: amount must be positive.");
            return false;
        }

        System.out.printf("External gateway processing %.2f KZT...%n", amountInKzt);
        // Simulation only: no real bank or card transaction is performed.
        System.out.println("Gateway: transaction approved.");
        return true;
    }
}