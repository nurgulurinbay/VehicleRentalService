package com.vehiclerental.builder;

import com.vehiclerental.model.Customer;
import com.vehiclerental.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Immutable booking created by RentalBookingBuilder.
 */
public class RentalBooking {
    private final String bookingId;
    private final Customer customer;
    private final Vehicle vehicle;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final double totalPrice;

    RentalBooking(Customer customer, Vehicle vehicle,
                  LocalDateTime startDate, LocalDateTime endDate,
                  double totalPrice) {
        this.bookingId = UUID.randomUUID().toString();
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId
                + "\nCustomer: " + customer.getName()
                + "\nVehicle: " + vehicle.getId() + " - "
                + vehicle.getBrand() + " " + vehicle.getModel()
                + "\nFrom: " + startDate
                + "\nUntil: " + endDate
                + "\nTotal price: " + String.format("%.2f", totalPrice);
    }
}