package com.vehiclerental.strategy;

import com.vehiclerental.vehicle.Vehicle;

public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {
        validate(vehicle, hours);
        return vehicle.getHourlyRate() * hours;
    }

    private void validate(Vehicle vehicle, int duration) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Rental duration must be positive.");
        }
    }
}

