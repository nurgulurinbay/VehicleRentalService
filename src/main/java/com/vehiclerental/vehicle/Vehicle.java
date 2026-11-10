package com.vehiclerental.vehicle;

public abstract class Vehicle {
    private final String id;
    private final String brand;
    private final String model;
    private final double hourlyRate;
    private final double dailyRate;
    private boolean available;

    protected Vehicle(String id, String brand, String model,
                      double hourlyRate, double dailyRate) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty.");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        if (hourlyRate < 0 || dailyRate < 0) {
            throw new IllegalArgumentException("Rates cannot be negative.");
        }

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getVehicleType();

    @Override
    public String toString() {
        return String.format(
                "%s | %s %s | hourly: %.2f | daily: %.2f | %s",
                id, brand, model, hourlyRate, dailyRate,
                available ? "AVAILABLE" : "RENTED"
        );
    }
}
