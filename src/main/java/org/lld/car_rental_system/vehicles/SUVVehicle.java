package org.lld.car_rental_system.vehicles;

import org.lld.car_rental_system.enums.VehicleType;

public class SUVVehicle extends Vehicle{
    private static final double RATE_MULTIPLIER = 1.5;

    public SUVVehicle(String registrationNumber, String model, double baseRentalPrice) {
        super(registrationNumber, model, VehicleType.SUV, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalAmount() * days * RATE_MULTIPLIER;
    }
}
