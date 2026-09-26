package org.lld.parking_lot;

import org.lld.parking_lot.enums.ParkingLotSlotType;
import org.lld.parking_lot.enums.VehicleType;
import org.lld.parking_lot.parking_lot.BikeParkingLot;
import org.lld.parking_lot.parking_lot.CarParkingLot;
import org.lld.parking_lot.parking_lot.ParkingSlot;
import org.lld.parking_lot.parking_lot.TruckParkingLot;
import org.lld.parking_lot.serives.ParkingLot;
import org.lld.parking_lot.vehicles.Bike;
import org.lld.parking_lot.vehicles.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        ParkingSlot bikeParkingSlot1=new BikeParkingLot("afsvgr", ParkingLotSlotType.BASIC);
        ParkingSlot bikeParkingSlot2=new BikeParkingLot("sfdfg",ParkingLotSlotType.PREMIUM);

        ParkingSlot carParkingSlot1=new CarParkingLot("fdgdg",ParkingLotSlotType.BASIC);
        ParkingSlot carParkingSlot2=new CarParkingLot("fdgdg",ParkingLotSlotType.PREMIUM);

        ParkingSlot truckParkingSlot1=new TruckParkingLot("fdgdg23fdd",ParkingLotSlotType.BASIC);
        ParkingSlot truckParkingSlot2=new TruckParkingLot("fdgdggerg",ParkingLotSlotType.PREMIUM);

        List<ParkingSlot> bikeSlots=new ArrayList<>(Arrays.asList(bikeParkingSlot1,bikeParkingSlot2));
        List<ParkingSlot> carSlots=new ArrayList<>(Arrays.asList(carParkingSlot1,carParkingSlot2));
        List<ParkingSlot> truckSlots=new ArrayList<>(Arrays.asList(truckParkingSlot1,truckParkingSlot2));

        ParkingLot parkingLot=new ParkingLot(bikeSlots,carSlots,truckSlots);


        // make Some Vehicle
        Bike bike=new Bike("9876",VehicleType.BIKE);
        Car car=new Car("345656",VehicleType.CAR);

        String ticket=parkingLot.occupy(bike);
        String ticket2=parkingLot.occupy(car);

        if(ticket!=null) System.out.println("parked Successfullt");
        Thread.sleep(5000);

        System.out.println("Removing Bike");
        parkingLot.vacant(ticket);

        Thread.sleep(2000);
        System.out.println();
        System.out.println("Removing Car");
        parkingLot.vacant(ticket2);

    }
}
