package org.lld.parking_lot.vehicles;

import org.lld.parking_lot.enums.VehicleType;

public abstract class Vehicle {
    protected String plateId;
    protected VehicleType vehicleType;

    public Vehicle(String plateId, VehicleType vehicleType) {
        this.plateId = plateId;
        this.vehicleType=vehicleType;
    }

    public  String getPlateId(){
        return plateId;
    }

    public  VehicleType getVehicleType(){
        return  vehicleType;
    }
}
