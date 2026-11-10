package com.vehiclerental.strategy;

import com.vehiclerental.vehicle.Vehicle;

public class DailyPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int days) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Rental duration must be positive.");
        }
        return vehicle.getDailyRate() * days;
    }
}
