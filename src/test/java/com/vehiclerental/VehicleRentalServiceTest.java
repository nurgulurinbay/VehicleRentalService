package com.vehiclerental;

import com.vehiclerental.vehicle.*;
import com.vehiclerental.factory.*;
import com.vehiclerental.strategy.*;
import com.vehiclerental.adapter.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRentalServiceTest {

    // FACTORY METHOD PATTERN
    @Test
    void testCarCreation() {
        VehicleCreator creator = new CarCreator(4);

        Vehicle car = creator.createVehicle(
                "C01", "Toyota", "Camry", 5000, 30000
        );

        assertNotNull(car);
        assertInstanceOf(Car.class, car);
    }

    @Test
    void testBikeCreation() {
        VehicleCreator creator = new BikeCreator(600);

        Vehicle bike = creator.createVehicle(
                "B01", "Honda", "CBR", 2000, 12000
        );

        assertNotNull(bike);
        assertInstanceOf(Bike.class, bike);
    }

    @Test
    void testVanCreation() {
        VehicleCreator creator = new VanCreator(1500.0);

        Vehicle van = creator.createVehicle(
                "V01", "Mercedes", "Sprinter", 8000, 50000
        );

        assertNotNull(van);
        assertInstanceOf(Van.class, van);
    }

    // STRATEGY PATTERN
    @Test
    void testHourlyPricing() {
        Vehicle car = new CarCreator(4).createVehicle(
                "C01", "Toyota", "Camry", 5000, 30000
        );

        PricingStrategy strategy = new HourlyPricingStrategy();

        double price = strategy.calculatePrice(car, 3);

        assertEquals(15000.0, price, 0.01);
    }

    @Test
    void testDailyPricing() {
        Vehicle car = new CarCreator(4).createVehicle(
                "C01", "Toyota", "Camry", 5000, 30000
        );

        PricingStrategy strategy = new DailyPricingStrategy();

        double price = strategy.calculatePrice(car, 2);

        assertEquals(60000.0, price, 0.01);
    }

    @Test
    void testPricingContext() {
        Vehicle car = new CarCreator(4).createVehicle(
                "C01", "Toyota", "Camry", 5000, 30000
        );

        PricingContext context = new PricingContext(new HourlyPricingStrategy());

        assertEquals(10000.0, context.calculatePrice(car, 2), 0.01);
    }

    // ADAPTER PATTERN
    @Test
    void testPaymentAdapter() {
        ExternalPaymentGateway gateway =
                new ExternalPaymentGateway();

        PaymentProcessor processor =
                new PaymentAdapter(gateway);

        assertDoesNotThrow(() -> processor.processPayment(10000));
    }
}
