package org.lld.parking_lot.parking_lot;

import lombok.Getter;
import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.enums.VehicleType;
import org.lld.parking_lot.factory.FeeCalcStragetyfactory;
import org.lld.parking_lot.fee_stragety.FeeCalcStragety;


@Getter
public abstract class ParkingSlot {
    private final String id;
    private final ParkingLotSlotType parkingLotSlotType;
    private final VehicleType vehicleType;

    private boolean isOccupied;
    private long start;

    private FeeCalcStragety feeCalcStragety;


    public ParkingSlot(String id, ParkingLotSlotType parkingLotSlotType,VehicleType vehicleType){
        this.id=id;
        this.parkingLotSlotType=parkingLotSlotType;
        this.vehicleType=vehicleType;

        this.feeCalcStragety= FeeCalcStragetyfactory.getFeeStragety(parkingLotSlotType);

        isOccupied=false;
    }

    public String occupy(){
        if(isOccupied) return  null;

        this.isOccupied=true;
        this.start=System.currentTimeMillis()/1000;

        return  id;
    }

    public boolean vacate(){
        if(!isOccupied) return false;

        this.isOccupied=false;

        return  true;
    }

    public double calcPrice(){
        return feeCalcStragety.calcFees(vehicleType,(double)(System.currentTimeMillis()/1000-start));
    }
}
