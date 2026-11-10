package com.vehiclerental.builder;

import com.vehiclerental.model.Customer;
import com.vehiclerental.vehicle.Vehicle;

import java.time.LocalDateTime;

/**
 * Builder: constructs a RentalBooking step by step.
 */
public class RentalBookingBuilder {
    private Customer customer;
    private Vehicle vehicle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalPrice;

    public RentalBookingBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public RentalBookingBuilder setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public RentalBookingBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public RentalBookingBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * The pricing module (Member 2) calculates this value and passes it here.
     */
    public RentalBookingBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public RentalBooking build() {
        if (customer == null) {
            throw new IllegalStateException("Customer must be specified.");
        }
        if (vehicle == null) {
            throw new IllegalStateException("Vehicle must be specified.");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalStateException("Start and end dates must be specified.");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalStateException("End date must be after start date.");
        }
        if (totalPrice == null || totalPrice < 0) {
            throw new IllegalStateException("A valid total price must be specified.");
        }
        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Selected vehicle is not available.");
        }

        return new RentalBooking(customer, vehicle, startDate, endDate, totalPrice);
    }
}

