package org.lld.car_rental_system.core;

import lombok.Getter;
import org.lld.car_rental_system.enums.VehicleStatus;
import org.lld.car_rental_system.utilities.Location;
import org.lld.car_rental_system.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    @Getter
    private final String  id;
    @Getter
    private String name;
    @Getter
    private Location location;
    private Map<String, Vehicle> vehicles;

    public RentalStore(String id,String name,Location location){
        this.id=id;
        this.name=name;
        this.location=location;
        vehicles=new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.put(vehicle.getRegistrationId(),vehicle);
    }

    public void removeVehicle(String registrationNo){
        vehicles.remove(registrationNo);
    }

    public Vehicle getVehicle(String registrationNo){
        return vehicles.get(registrationNo);
    }

    public List<Vehicle> getAvailableVehicle(){
        List<Vehicle> availableVehicle=new ArrayList<>();

        for(Vehicle vehicle:vehicles.values()){
            if(vehicle.getVehicleStatus()== VehicleStatus.AVAILABLE){
                availableVehicle.add(vehicle);
            }
        }

        return  availableVehicle;
    }

    private boolean isVehicleAvailable(String registrationNo){ // Todo: Add Start and End Date Checks
        Vehicle vehicle=vehicles.get(registrationNo);
        return  vehicle!=null&&vehicle.getVehicleStatus()==VehicleStatus.AVAILABLE;
    }
}
