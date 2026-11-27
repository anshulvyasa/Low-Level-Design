package org.lld.ticket_booking_system.models;

import lombok.Getter;
import org.lld.ticket_booking_system.enums.BookingStatus;

import java.util.List;

@Getter
public class Booking {
    private final int id;
    private final Show show;
    private final User user;
    private final List<Seat> seats;
    private BookingStatus bookingStatus;

    public Booking(int id,Show show,User user,List<Seat> seats){
        this.id=id;
        this.show=show;
        this.user=user;
        this.seats=seats;
        this.bookingStatus=BookingStatus.CREATED;
    }

    public boolean isConfirmed(){
        return  bookingStatus==BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception{
        if(!(bookingStatus==BookingStatus.CREATED)){
            throw new Exception("Cannot confirm a booking that is not in the Created state.");
        }
        this.bookingStatus=BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws Exception{
        if(!(bookingStatus==BookingStatus.CREATED)){
            throw new Exception("Cannot expire a booking that is not in the Created state.");
        }

        this.bookingStatus=BookingStatus.EXPIRED;
    }
}
