package com.vehiclerental.adapter;


public class ExternalPaymentGateway {
    public boolean makeTransaction(double amountInKzt) {
        if (amountInKzt <= 0) {
            System.out.println("Gateway rejected transaction: amount must be positive.");
            return false;
        }

        System.out.printf("External gateway processing %.2f KZT...%n", amountInKzt);

        System.out.println("Gateway: transaction approved.");
        return true;
    }
}