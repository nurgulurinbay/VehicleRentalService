package com.vehiclerental.strategy;

import com.vehiclerental.vehicle.Vehicle;

/**
 * Context class. It delegates the calculation to the selected strategy.
 */
public class PricingContext {
    private PricingStrategy pricingStrategy;

    public PricingContext(PricingStrategy pricingStrategy) {
        setPricingStrategy(pricingStrategy);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        if (pricingStrategy == null) {
            throw new IllegalArgumentException("Pricing strategy cannot be null.");
        }
        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(Vehicle vehicle, int duration) {
        return pricingStrategy.calculatePrice(vehicle, duration);
    }
}

