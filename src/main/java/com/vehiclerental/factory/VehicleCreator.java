package com.vehiclerental.factory;

import com.vehiclerental.vehicle.Vehicle;

public abstract class VehicleCreator {
    public abstract Vehicle createVehicle(
            String id,
            String brand,
            String model,
            double hourlyRate,
            double dailyRate
    );
}
