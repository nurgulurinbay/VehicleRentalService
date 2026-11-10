package com.vehiclerental.strategy;

import com.vehiclerental.vehicle.Vehicle;

/**
 * Strategy interface: each pricing algorithm implements calculatePrice.
 * duration is measured in hours for hourly pricing and days for daily pricing.
 */
public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int duration);
}
