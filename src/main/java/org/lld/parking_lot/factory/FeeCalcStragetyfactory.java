package org.lld.parking_lot.factory;

import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.fee_stragety.BasicFeeCalcStragety;
import org.lld.parking_lot.fee_stragety.FeeCalcStragety;
import org.lld.parking_lot.fee_stragety.PremiumFeeCalcStragety;

public class FeeCalcStragetyfactory {
    public  static FeeCalcStragety getFeeStragety(ParkingLotSlotType parkingLotSlotType){
        return switch (parkingLotSlotType){
            case BASIC -> new BasicFeeCalcStragety();
            case PREMIUM -> new PremiumFeeCalcStragety();
            default ->  throw new IllegalArgumentException("Current Fee Stragety is not Supported");
        };
    }
}
