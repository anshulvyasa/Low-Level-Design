package org.lld.car_rental_system.vehicles;

import org.lld.car_rental_system.enums.VehicleType;

public class AutoVehicle extends  Vehicle{
    private static final double RATE_MULTIPLIER = 1.2;

    public AutoVehicle(
            String registrationNumber, String model, double baseRentalPrice) {
        super(registrationNumber, model, VehicleType.AUTO, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalAmount() * days * RATE_MULTIPLIER;
    }
}
