package org.lld.car_rental_system.vehicles;

import org.lld.car_rental_system.enums.VehicleType;

public class BikeVehicle extends Vehicle{
    private static final double RATE_MULTIPLIER = 0.5;

    public BikeVehicle(
            String registrationNumber, String model,double baseRentalPrice) {
        super(registrationNumber, model, VehicleType.BIKE, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalAmount() * days * RATE_MULTIPLIER;
    }
}
