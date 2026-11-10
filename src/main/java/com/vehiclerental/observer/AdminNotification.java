package com.vehiclerental.observer;

import com.vehiclerental.vehicle.Vehicle;

/** Concrete observer that reports availability changes to the administrator. */
public class AdminNotification implements AvailabilityObserver {
    private final String adminName;

    public AdminNotification(String adminName) {
        if (adminName == null || adminName.isBlank()) {
            throw new IllegalArgumentException("Admin name cannot be empty.");
        }
        this.adminName = adminName;
    }

    @Override
    public void onAvailabilityChanged(Vehicle vehicle) {
        System.out.printf("Admin alert for %s: vehicle %s is %s.%n",
                adminName, vehicle.getId(),
                vehicle.isAvailable() ? "AVAILABLE" : "RENTED");
    }
}

