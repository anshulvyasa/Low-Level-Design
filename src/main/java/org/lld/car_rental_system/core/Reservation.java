package org.lld.car_rental_system.core;

import lombok.Getter;
import org.lld.car_rental_system.enums.ReservationStatus;
import org.lld.car_rental_system.enums.VehicleStatus;
import org.lld.car_rental_system.user.User;
import org.lld.car_rental_system.utilities.Date;
import org.lld.car_rental_system.vehicles.Vehicle;


@Getter
public class Reservation {
    private final long reservationId;
    private final User user;

    private Date startDate;
    private Date endDate;

    private Vehicle vehicle;

    private RentalStore pickUpStore;
    private RentalStore dropStore;

    private ReservationStatus reservationStatus;

    private double amountToBePaid;


    public Reservation(long reservationId,User user,Date startDate,Date endDate,Vehicle vehicle,RentalStore pickUpStore,RentalStore dropStore){
        this.reservationId=reservationId;
        this.user=user;
        this.startDate=startDate;
        this.endDate=endDate;
        this.vehicle=vehicle;
        this.pickUpStore=pickUpStore;
        this.dropStore=dropStore;
        this.reservationStatus=ReservationStatus.PENDING;

        long diff=endDate.getTime()-startDate.getTime();
        long days=diff/(24*60*60*1000)+1;
        this.amountToBePaid=vehicle.calculateRentalFee((int)days);
    }

    public void confirmReservation(){
        if(reservationStatus==ReservationStatus.PENDING){
            reservationStatus=ReservationStatus.CONFIRMED;
            vehicle.setVehicleStatus(VehicleStatus.RESERVED);
        }
    }

   public void startRental(){
        if(reservationStatus==ReservationStatus.CONFIRMED){
            reservationStatus=ReservationStatus.IN_PROGRESS;
            vehicle.setVehicleStatus(VehicleStatus.RENTED);
        }
   }

   public void completeRental(){
        if(reservationStatus==ReservationStatus.IN_PROGRESS){
            reservationStatus=ReservationStatus.COMPLETED;
            vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        }
   }

   public void cancelReservation(){
        if(reservationStatus==ReservationStatus.PENDING||reservationStatus==ReservationStatus.CONFIRMED){
            reservationStatus=ReservationStatus.CANCELLED;
            vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        }
   }

}
