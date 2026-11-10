package com.vehiclerental.factory;

import com.vehiclerental.vehicle.Car;
import com.vehiclerental.vehicle.Vehicle;

public class CarCreator extends VehicleCreator {
    private final int passengerCapacity;

    public CarCreator(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public Vehicle createVehicle(String id, String brand, String model,
                                 double hourlyRate, double dailyRate) {
        return new Car(id, brand, model, hourlyRate, dailyRate, passengerCapacity);
    }
}

