package org.lld.parking_lot.parking_lot;

import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.enums.VehicleType;

public class TruckParkingLot extends ParkingSlot{
    public TruckParkingLot(String id, ParkingLotSlotType parkingLotSlotType) {
        super(id, parkingLotSlotType, VehicleType.TRUCK);
    }
}
