package com.vehiclerental.observer;

import com.vehiclerental.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleAvailabilitySubject {
    private final Vehicle vehicle;
    private final List<AvailabilityObserver> observers = new ArrayList<>();

    public VehicleAvailabilitySubject(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        this.vehicle = vehicle;
    }

    public void subscribe(AvailabilityObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null.");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(AvailabilityObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (AvailabilityObserver observer : List.copyOf(observers)) {
            observer.onAvailabilityChanged(vehicle);
        }
    }

    public void setAvailable(boolean available) {
        if (vehicle.isAvailable() == available) {
            return;
        }
        vehicle.setAvailable(available);
        notifyObservers();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getObserverCount() {
        return observers.size();
    }
}
