package com.vehiclerental.vehicle;

/**
 * In this project, Bike means a motorbike/motorcycle.
 */
public class Bike extends Vehicle {
    private final int engineCapacityCc;

    public Bike(String id, String brand, String model,
                double hourlyRate, double dailyRate, int engineCapacityCc) {
        super(id, brand, model, hourlyRate, dailyRate);
        if (engineCapacityCc < 1) {
            throw new IllegalArgumentException("Engine capacity must be positive.");
        }
        this.engineCapacityCc = engineCapacityCc;
    }

    public int getEngineCapacityCc() {
        return engineCapacityCc;
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}