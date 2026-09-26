package org.lld.parking_lot.fee_stragety;

import org.lld.parking_lot.enums.VehicleType;

public interface FeeCalcStragety {
    double calcFees(VehicleType vehicleType,double duration);
}
