package org.lld.car_rental_system.core;

import lombok.Setter;
import org.lld.car_rental_system.payment.PaymentStragety;
import org.lld.car_rental_system.user.User;
import org.lld.car_rental_system.utilities.Date;
import org.lld.car_rental_system.vehicles.Vehicle;



import java.util.HashMap;
import java.util.Map;

public class RentalSystem {
    private static RentalSystem rentalSystem;

    private Map<String,RentalStore> rentalStoreMap;
    private ReservationManager reservationManager;
    private Map<Integer,User> userMap;

    @Setter
    private PaymentStragety paymentStragety;

    private RentalSystem(){
        this.rentalStoreMap=new HashMap<>();
        this.reservationManager=ReservationManager.getReservationManager();
        this.userMap=new HashMap<>();
    }

    public  static  RentalSystem getRentalSystem(){
        if(rentalSystem==null){
            rentalSystem=new RentalSystem();
        }

        return  rentalSystem;
    }

    public void addRentalStore(RentalStore rentalStore){
        rentalStoreMap.put(rentalStore.getId(),rentalStore);
    }

    public void removeRentalStore(String rentalStoreId){
        rentalStoreMap.remove(rentalStoreId);
    }

    public Reservation createReservation(int userId, Date startDate,String vehicleRegistrationId, Date endDate,String pickUpStoreId,String dropStoreId) {
       User user=userMap.get(userId);
       RentalStore pickUpStore=rentalStoreMap.get(pickUpStoreId);
       RentalStore dropStore=rentalStoreMap.get(dropStoreId);

       Vehicle vehicle=pickUpStore!=null? pickUpStore.getVehicle(vehicleRegistrationId):null;

       if(user!=null&&pickUpStore!=null&&dropStore!=null&&vehicle!=null){
           return  reservationManager.createReservation(startDate,endDate,user,vehicle,pickUpStore,dropStore);
       }

       return  null;
    }

    public void processPayment(long reservationId){
        Reservation reservation=reservationManager.getReservationById(reservationId);

        if(reservation!=null){
            boolean status=paymentStragety.processPayment(reservation.getAmountToBePaid());

            if(status){
                reservationManager.confirmReservation(reservationId);
            }
        }
    }

    public void startRental(int reservationId){
        reservationManager.startRental(reservationId);
    }

    public  void completeRental(int reservationId){
        reservationManager.completeRental(reservationId);
    }

    public void cancelledRental(int reservationId){
        reservationManager.cancelReservation(reservationId);
    }

    public void registerUser(User user){
        int userID = user.getId();
        if(userMap.containsKey(userID)){
            System.out.println("User with id : " + userID + "Already exists in the system");
            return;
        }
        userMap.put(userID , user);
    }
}
