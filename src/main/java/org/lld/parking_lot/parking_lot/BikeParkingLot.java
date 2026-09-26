package org.lld.parking_lot.parking_lot;

import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.enums.VehicleType;

public class BikeParkingLot extends ParkingSlot {
    public BikeParkingLot(String id, ParkingLotSlotType parkingLotSlotType) {
        super(id, parkingLotSlotType, VehicleType.BIKE);
    }
}
