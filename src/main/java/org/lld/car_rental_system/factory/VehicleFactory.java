package org.lld.car_rental_system.factory;

import org.lld.car_rental_system.enums.VehicleType;
import org.lld.car_rental_system.vehicles.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String registationId, String model, VehicleType type,double baseRentalPrice){
        return switch (type){
            case VehicleType.ECONOMY -> new EconomyVehicle(registationId,model,baseRentalPrice);
            case VehicleType.LUXURY -> new LuxuryVehicle(registationId,model,baseRentalPrice);
            case VehicleType.SUV -> new SUVVehicle(registationId,model,baseRentalPrice);
            case VehicleType.BIKE -> new BikeVehicle(registationId,model,baseRentalPrice);
            case VehicleType.AUTO -> new AutoVehicle(registationId,model,baseRentalPrice);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        };
    }
}
