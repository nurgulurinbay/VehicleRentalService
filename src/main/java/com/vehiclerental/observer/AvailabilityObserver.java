package com.vehiclerental.observer;

import com.vehiclerental.vehicle.Vehicle;

/**
 * Observer interface: subscribers receive vehicle availability changes.
 */
public interface AvailabilityObserver {
    void onAvailabilityChanged(Vehicle vehicle);
}
