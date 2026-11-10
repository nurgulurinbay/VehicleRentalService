package com.vehiclerental.vehicle;

public class Van extends Vehicle {
    private final double cargoCapacityKg;

    public Van(String id, String brand, String model,
               double hourlyRate, double dailyRate, double cargoCapacityKg) {
        super(id, brand, model, hourlyRate, dailyRate);
        if (cargoCapacityKg <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be positive.");
        }
        this.cargoCapacityKg = cargoCapacityKg;
    }

    public double getCargoCapacityKg() {
        return cargoCapacityKg;
    }

    @Override
    public String getVehicleType() {
        return "Van";
    }
}