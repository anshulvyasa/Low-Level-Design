package org.lld.car_rental_system.vehicles;

import lombok.Getter;
import lombok.Setter;
import org.lld.car_rental_system.enums.VehicleStatus;
import org.lld.car_rental_system.enums.VehicleType;

@Getter
public abstract class Vehicle {
    private final String registrationId;
    private final String model;

    @Setter
    private VehicleType vehicleType;
    @Setter
    private VehicleStatus vehicleStatus;
    @Setter
    private double baseRentalAmount;

    public Vehicle(String registrationId,String model,VehicleType vehicleType,double baseRentalAmount){
         this.registrationId=registrationId;
         this.model=model;
         this.vehicleType=vehicleType;
         this.baseRentalAmount=baseRentalAmount;
    }

    public  abstract double calculateRentalFee(int days);
}
