package com.vehiclerental.observer;

import com.vehiclerental.model.Customer;
import com.vehiclerental.vehicle.Vehicle;

public class CustomerNotification implements AvailabilityObserver {
    private final Customer customer;

    public CustomerNotification(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.customer = customer;
    }

    @Override
    public void onAvailabilityChanged(Vehicle vehicle) {
        String status = vehicle.isAvailable() ? "is now AVAILABLE" : "is now RENTED";
        System.out.printf("Notification to %s (%s): %s %s %s %s.%n",
                customer.getName(), customer.getEmail(),
                vehicle.getVehicleType(), vehicle.getBrand(),
                vehicle.getModel(), status);
    }
}
