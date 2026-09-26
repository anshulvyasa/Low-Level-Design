package org.lld.parking_lot.factory;

import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.enums.VehicleType;
import org.lld.parking_lot.parking_lot.BikeParkingLot;
import org.lld.parking_lot.parking_lot.CarParkingLot;
import org.lld.parking_lot.parking_lot.ParkingSlot;
import org.lld.parking_lot.parking_lot.TruckParkingLot;

public class ParkingSlotFactory {
    public  static ParkingSlot getParkingSlot(VehicleType vehicleType, String id, ParkingLotSlotType parkingLotSlotType){
        return switch (vehicleType){
            case BIKE -> new BikeParkingLot(id,parkingLotSlotType);
            case CAR -> new CarParkingLot(id,parkingLotSlotType);
            case TRUCK -> new TruckParkingLot(id,parkingLotSlotType);
            default -> throw new IllegalArgumentException("Current Vehicle is Not Supported");
        };
    }
}
