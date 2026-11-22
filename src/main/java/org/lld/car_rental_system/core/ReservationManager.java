package org.lld.car_rental_system.core;

import org.lld.car_rental_system.user.User;
import org.lld.car_rental_system.utilities.Date;
import org.lld.car_rental_system.vehicles.Vehicle;


import java.util.HashMap;
import java.util.Map;

public class ReservationManager {
    private static ReservationManager reservationManager;
    private Map<Long,Reservation> reservationMap;
    long reservationCounter;

    private  ReservationManager(){
        reservationCounter=1;
        reservationMap=new HashMap<>();
    }

    public static ReservationManager getReservationManager(){
        if(reservationManager==null){
            reservationManager=new ReservationManager();
        }
        return reservationManager;
    }

    public Reservation createReservation(Date startDate, Date endDate, User user, Vehicle vehicle, RentalStore pickupStore, RentalStore dropStore){
        Reservation reservation=new Reservation(reservationCounter++,user,startDate,endDate,vehicle,pickupStore,dropStore);
        reservationMap.put(reservation.getReservationId(),reservation);

        return  reservation;
    }

    public void confirmReservation(long reservationId){
        Reservation reservation=reservationMap.get(reservationId);
        if(reservation!=null){
            reservation.confirmReservation();
        }
    }

    public void startRental(long reservationId){
        Reservation reservation=reservationMap.get(reservationId);
        if(reservation!=null){
            reservation.startRental();
        }
    }

    public void completeRental(long reservationId){
        Reservation reservation=reservationMap.get(reservationId);
        if(reservation!=null){
            reservation.completeRental();
        }
    }

    public void cancelReservation(long reservationId){
        Reservation reservation=reservationMap.get(reservationId);
        if(reservation!=null){
            reservation.cancelReservation();
        }
    }

    public  Reservation getReservationById(long reservationId){
        return reservationMap.get(reservationId);
    }
}
