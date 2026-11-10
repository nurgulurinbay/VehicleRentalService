package com.vehiclerental.vehicle;

public class Car extends Vehicle {
    private final int passengerCapacity;

    public Car(String id, String brand, String model,
               double hourlyRate, double dailyRate, int passengerCapacity) {
        super(id, brand, model, hourlyRate, dailyRate);
        if (passengerCapacity < 1) {
            throw new IllegalArgumentException("Passenger capacity must be positive.");
        }
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

