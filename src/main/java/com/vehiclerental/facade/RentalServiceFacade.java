package com.vehiclerental.facade;

import com.vehiclerental.adapter.PaymentProcessor;
import com.vehiclerental.builder.RentalBooking;
import com.vehiclerental.builder.RentalBookingBuilder;
import com.vehiclerental.model.Customer;
import com.vehiclerental.observer.VehicleAvailabilitySubject;
import com.vehiclerental.strategy.PricingContext;
import com.vehiclerental.strategy.HourlyPricingStrategy;
import com.vehiclerental.strategy.DailyPricingStrategy;
import com.vehiclerental.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Facade: exposes a small API while coordinating catalog, pricing,
 * booking, payment, and availability-notification subsystems.
 */
public class RentalServiceFacade {
    private final VehicleCatalog catalog;
    private final PricingContext pricingContext;
    private final PaymentProcessor paymentProcessor;
    private final Map<String, VehicleAvailabilitySubject> availabilitySubjects =
            new LinkedHashMap<>();
    private final Map<String, RentalBooking> activeBookings = new LinkedHashMap<>();

    public RentalServiceFacade(VehicleCatalog catalog,
                               PricingContext pricingContext,
                               PaymentProcessor paymentProcessor) {
        if (catalog == null || pricingContext == null || paymentProcessor == null) {
            throw new IllegalArgumentException("Facade dependencies cannot be null.");
        }
        this.catalog = catalog;
        this.pricingContext = pricingContext;
        this.paymentProcessor = paymentProcessor;
    }

    public void registerVehicle(Vehicle vehicle) {
        catalog.addVehicle(vehicle);
        availabilitySubjects.put(vehicle.getId(),
                new VehicleAvailabilitySubject(vehicle));
    }

    public void displayAvailableVehicles() {
        System.out.println("\n=== AVAILABLE VEHICLES ===");
        catalog.getAvailableVehicles().forEach(System.out::println);
    }

    public Vehicle findVehicle(String vehicleId) {
        return catalog.findById(vehicleId);
    }

    public VehicleAvailabilitySubject getAvailabilitySubject(String vehicleId) {
        VehicleAvailabilitySubject subject = availabilitySubjects.get(vehicleId);
        if (subject == null) {
            throw new IllegalArgumentException("Unknown vehicle ID: " + vehicleId);
        }
        return subject;
    }

    /**
     * Calculates the price, builds the booking, processes payment,
     * and marks the vehicle unavailable after successful payment.
     *
     * duration is hours when hourlyPricing is true; otherwise days.
     */
    public RentalBooking bookVehicle(Customer customer, String vehicleId,
                                     LocalDateTime start, LocalDateTime end,
                                     int duration, boolean hourlyPricing) {
        Vehicle vehicle = catalog.findById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found: " + vehicleId);
        }
        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle is not available.");
        }
        if (customer == null || start == null || end == null || !end.isAfter(start)) {
            throw new IllegalArgumentException("Valid customer and rental dates are required.");
        }

        // Select the pricing algorithm for this booking (Strategy pattern).
        pricingContext.setPricingStrategy(hourlyPricing
                ? new HourlyPricingStrategy()
                : new DailyPricingStrategy());
        double price = pricingContext.calculatePrice(vehicle, duration);

        RentalBooking booking = new RentalBookingBuilder()
                .setCustomer(customer)
                .setVehicle(vehicle)
                .setStartDate(start)
                .setEndDate(end)
                .setTotalPrice(price)
                .build();

        boolean paymentSuccessful = paymentProcessor.processPayment(price);
        if (!paymentSuccessful) {
            throw new IllegalStateException("Payment failed. Booking was not confirmed.");
        }

        activeBookings.put(booking.getBookingId(), booking);
        getAvailabilitySubject(vehicleId).setAvailable(false);
        System.out.println("Booking confirmed: " + booking.getBookingId());
        return booking;
    }

    /**
     * Returns a vehicle for an active booking and notifies observers.
     */
    public void returnVehicle(String bookingId) {
        RentalBooking booking = activeBookings.remove(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Active booking not found: " + bookingId);
        }

        String vehicleId = booking.getVehicle().getId();
        getAvailabilitySubject(vehicleId).setAvailable(true);
        System.out.println("Vehicle returned successfully: " + vehicleId);
    }
}

