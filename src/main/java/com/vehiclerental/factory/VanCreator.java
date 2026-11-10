package com.vehiclerental.factory;

import com.vehiclerental.vehicle.Van;
import com.vehiclerental.vehicle.Vehicle;

public class VanCreator extends VehicleCreator {
    private final double cargoCapacityKg;

    public VanCreator(double cargoCapacityKg) {
        this.cargoCapacityKg = cargoCapacityKg;
    }

    @Override
    public Vehicle createVehicle(String id, String brand, String model,
                                 double hourlyRate, double dailyRate) {
        return new Van(id, brand, model, hourlyRate, dailyRate, cargoCapacityKg);
    }
}

