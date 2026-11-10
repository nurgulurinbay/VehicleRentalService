package com.vehiclerental.factory;

import com.vehiclerental.vehicle.Vehicle;

/**
 * Factory Method: subclasses decide which concrete Vehicle to create.
 */
public abstract class VehicleCreator {
    public abstract Vehicle createVehicle(
            String id,
            String brand,
            String model,
            double hourlyRate,
            double dailyRate
    );
}
