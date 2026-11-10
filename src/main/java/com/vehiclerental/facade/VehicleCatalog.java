package com.vehiclerental.facade;

import com.vehiclerental.vehicle.Vehicle;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** Internal subsystem component managed by RentalServiceFacade. */
public class VehicleCatalog {
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null.");
        if (vehicles.containsKey(vehicle.getId())) {
            throw new IllegalArgumentException("Duplicate vehicle ID: " + vehicle.getId());
        }
        vehicles.put(vehicle.getId(), vehicle);
    }

    public Vehicle findById(String id) {
        return vehicles.get(id);
    }

    public Collection<Vehicle> getAllVehicles() {
        return vehicles.values();
    }

    public Collection<Vehicle> getAvailableVehicles() {
        return vehicles.values().stream().filter(Vehicle::isAvailable).toList();
    }
}
