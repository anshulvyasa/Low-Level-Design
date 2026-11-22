package org.lld.car_rental_system.vehicles;
import org.lld.car_rental_system.enums.VehicleType;

public class EconomyVehicle extends  Vehicle{
    private static  final double RATE_MULTIPLIER=1.0;

    public EconomyVehicle(String registrationId, String model,double baseRentalAmount) {
        super(registrationId, model, VehicleType.ECONOMY, baseRentalAmount);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalAmount()*days*RATE_MULTIPLIER;
    }
}
