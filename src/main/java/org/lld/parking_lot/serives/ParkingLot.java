package org.lld.parking_lot.serives;

import org.lld.parking_lot.enums.VehicleType;
import org.lld.parking_lot.factory.PaymentFactory;
import org.lld.parking_lot.parking_lot.ParkingSlot;
import org.lld.parking_lot.payments.PaymentStragety;
import org.lld.parking_lot.vehicles.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ParkingLot {
    Map<VehicleType, List<ParkingSlot>> availableSlot;
    Map<String,ParkingSlot> occupiedSlot;

    public  ParkingLot(List<ParkingSlot> bikeSlots,List<ParkingSlot> carSlots,List<ParkingSlot> truckSlots){
        availableSlot=new HashMap<>();
        occupiedSlot=new HashMap<>();


        availableSlot.put(VehicleType.BIKE,bikeSlots);
        availableSlot.put(VehicleType.CAR,carSlots);
        availableSlot.put(VehicleType.TRUCK,truckSlots);
    }

    public String occupy(Vehicle vehicle){
        if(availableSlot.get(vehicle.getVehicleType())==null) return  null;

        ParkingSlot slot=availableSlot.get(vehicle.getVehicleType()).getFirst();

        String ticket= slot.occupy();
        if(ticket!=null){
            availableSlot.get(vehicle.getVehicleType()).remove(slot);
            occupiedSlot.put(ticket,slot);
        }

        return ticket;
    }

    public void vacant(String ticket){
         ParkingSlot slot=occupiedSlot.get(ticket);
         if(slot==null) return;

         // Fee Calculation and PaymentStragety Processing
        double feesToBePaid=slot.calcPrice();

        Scanner sc=new Scanner(System.in);
        System.out.println("Select a option to continue your payment");
        System.out.println("Selct 1 for Credit Card Payment");
        System.out.println("Select 2 for Debit Card Payment");
        System.out.println("Select 3 for UPI Payment");
        int choise=sc.nextInt();

        PaymentStragety paymentStragety= PaymentFactory.getPaymentStragety(choise);
        paymentStragety.processPayment(feesToBePaid);

        occupiedSlot.remove(ticket);
        availableSlot.get(slot.getVehicleType()).add(slot);
    }
}
