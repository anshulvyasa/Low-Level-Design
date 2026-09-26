package org.lld.parking_lot.fee_stragety;

import org.lld.parking_lot.enums.VehicleType;

public class PremiumFeeCalcStragety implements  FeeCalcStragety{
    @Override
    public double calcFees(VehicleType vehicleType, double duration) {
        return  switch (vehicleType){
            case BIKE ->   15.0*duration;
            case CAR ->    20.0*duration;
            case TRUCK ->  25.0*duration;
            default -> throw new IllegalArgumentException("We Current Support This vehicle");
        };
    }
}
