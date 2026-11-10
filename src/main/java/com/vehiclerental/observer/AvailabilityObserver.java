package com.vehiclerental.observer;

import com.vehiclerental.vehicle.Vehicle;

public interface AvailabilityObserver {
    void onAvailabilityChanged(Vehicle vehicle);
}
