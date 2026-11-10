package com.vehiclerental.strategy;

import com.vehiclerental.vehicle.Vehicle;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int duration);
}
