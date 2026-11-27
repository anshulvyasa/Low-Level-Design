package org.lld.ticket_booking_system.services;

import org.lld.ticket_booking_system.interfaces.ISeatLockProvider;
import org.lld.ticket_booking_system.models.Booking;
import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Show;
import org.lld.ticket_booking_system.models.User;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<Integer, Booking> bookingMap;
    private final AtomicInteger bookingCounter;
    private ISeatLockProvider seatLockProvider;

    public BookingService(ISeatLockProvider seatLockProvider){
        bookingMap=new ConcurrentHashMap<>();
        bookingCounter=new AtomicInteger(0);
        this.seatLockProvider=seatLockProvider;
    }

    // get a single Booking
    public Booking getBooking(int bookingId) throws Exception{
        if(!bookingMap.containsKey(bookingId)) throw new Exception("Booking Doesn't exist");

        return bookingMap.get(bookingId);
    }

    // get All Booking
    public List<Booking> getAllBooking(Show show){
        List<Booking> bookings=new ArrayList<>();

        for(Booking booking:bookingMap.values()){
            if(booking.getShow().equals(show)) bookings.add(booking);
        }

        return bookings;
    }

    // Create Booking
    public Booking createBooking(Show show, User user, List<Seat> seats) throws Exception{
         if(isAnySeatAlreadyBooked(show,seats)) throw new Exception("Seat Already Booked");

         seatLockProvider.lock(user,show,seats);

         int id=bookingCounter.incrementAndGet();
         Booking booking=new Booking(id,show,user,seats);

         bookingMap.put(id,booking);
         return booking;
    }

    public List<Seat> getBookedSeat(Show show){
        return bookingMap.values().stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public boolean isAnySeatAlreadyBooked(Show show,List<Seat> seats){
         List<Seat> bookedSeat=getBookedSeat(show);

         for(Seat seat:seats){
             if(bookedSeat.contains(seat)) return true;
         }

         return false;
    }

    public void confirmBooking(User user,Booking booking) throws Exception{
        if(!booking.getUser().equals(user)) return;

        for(Seat seat:booking.getSeats()){
            if(!seatLockProvider.validateLock(user,booking.getShow(),seat))
                throw new Exception("Acquired Lock is either invalid or has Expired");
        }

        booking.confirmBooking();
    }
}
