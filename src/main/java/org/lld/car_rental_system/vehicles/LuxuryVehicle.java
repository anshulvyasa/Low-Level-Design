package org.lld.car_rental_system.vehicles;

import org.lld.car_rental_system.enums.VehicleType;

public class LuxuryVehicle extends  Vehicle{
    private  static  final  double RATE_MULTIPLIER=2.5;
    private  static  final double PREMIUM_FEE=50.0;

    public LuxuryVehicle(String registrationId,String model,double baseRentalAmount){
       super(registrationId,model, VehicleType.LUXURY,baseRentalAmount);
    }

    @Override
    public double calculateRentalFee(int days){
        return  getBaseRentalAmount()*days*RATE_MULTIPLIER+PREMIUM_FEE;
    }
}
