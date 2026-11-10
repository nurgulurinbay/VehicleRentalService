package com.vehiclerental.factory;

import com.vehiclerental.vehicle.Bike;
import com.vehiclerental.vehicle.Vehicle;

public class BikeCreator extends VehicleCreator {
    private final int engineCapacityCc;

    public BikeCreator(int engineCapacityCc) {
        this.engineCapacityCc = engineCapacityCc;
    }

    @Override
    public Vehicle createVehicle(String id, String brand, String model,
                                 double hourlyRate, double dailyRate) {
        return new Bike(id, brand, model, hourlyRate, dailyRate, engineCapacityCc);
    }
}