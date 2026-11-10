package com.vehiclerental;

import com.vehiclerental.adapter.ExternalPaymentGateway;
import com.vehiclerental.adapter.PaymentAdapter;
import com.vehiclerental.builder.RentalBooking;
import com.vehiclerental.factory.BikeCreator;
import com.vehiclerental.factory.CarCreator;
import com.vehiclerental.factory.VanCreator;
import com.vehiclerental.facade.RentalServiceFacade;
import com.vehiclerental.facade.VehicleCatalog;
import com.vehiclerental.model.Customer;
import com.vehiclerental.observer.AdminNotification;
import com.vehiclerental.observer.CustomerNotification;
import com.vehiclerental.strategy.HourlyPricingStrategy;
import com.vehiclerental.strategy.PricingContext;
import com.vehiclerental.vehicle.Vehicle;

import java.time.LocalDateTime;

/**
 * Integrated demonstration of all six patterns.
 * Console-based workflow for the team project.
 */
public class Main {
    public static void main(String[] args) {
        // FACTORY METHOD: create concrete vehicle objects.
        Vehicle car = new CarCreator(5).createVehicle(
                "CAR-001", "Toyota", "Camry", 15.0, 90.0);
        Vehicle bike = new BikeCreator(600).createVehicle(
                "BIKE-001", "Yamaha", "MT-07", 8.0, 45.0);
        Vehicle van = new VanCreator(1200).createVehicle(
                "VAN-001", "Ford", "Transit", 30.0, 170.0);

        // STRATEGY: select hourly pricing for this demonstration.
        PricingContext pricing = new PricingContext(new HourlyPricingStrategy());

        // ADAPTER: adapt the simulated external payment gateway.
        PaymentAdapter paymentAdapter =
                new PaymentAdapter(new ExternalPaymentGateway());

        // FACADE: provide one simple entry point to the rental subsystem.
        RentalServiceFacade rentalService = new RentalServiceFacade(
                new VehicleCatalog(), pricing, paymentAdapter);

        rentalService.registerVehicle(car);
        rentalService.registerVehicle(bike);
        rentalService.registerVehicle(van);

        // OBSERVER: subscribe a customer and an administrator to CAR-001.
        Customer customer = new Customer(
                "CUS-001", "Alex Morgan", "alex@example.com");

        rentalService.getAvailabilitySubject("CAR-001")
                .subscribe(new CustomerNotification(customer));
        rentalService.getAvailabilitySubject("CAR-001")
                .subscribe(new AdminNotification("Rental Admin"));

        rentalService.displayAvailableVehicles();

        System.out.println("\n=== HOURLY RENTAL PROCESS ===");
        LocalDateTime start = LocalDateTime.of(2026, 11, 12, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 12, 14, 0);

        // FACADE coordinates pricing, Builder, Adapter, and Observer.
        RentalBooking booking = rentalService.bookVehicle(
                customer, "CAR-001", start, end, 4, true);

        System.out.println("\n=== BOOKING DETAILS ===");
        System.out.println(booking);

        rentalService.displayAvailableVehicles();

        System.out.println("\n=== RETURN PROCESS ===");
        rentalService.returnVehicle(booking.getBookingId());

        System.out.println("\n=== DAILY RENTAL PROCESS ===");
        LocalDateTime dailyStart = LocalDateTime.of(2026, 11, 13, 9, 0);
        LocalDateTime dailyEnd = LocalDateTime.of(2026, 11, 15, 9, 0);
        RentalBooking dailyBooking = rentalService.bookVehicle(
                customer, "BIKE-001", dailyStart, dailyEnd, 2, false);

        System.out.println("\n=== DAILY BOOKING DETAILS ===");
        System.out.println(dailyBooking);

        System.out.println("\n=== RETURN DAILY RENTAL ===");
        rentalService.returnVehicle(dailyBooking.getBookingId());

        rentalService.displayAvailableVehicles();
    }
}
