package org.lld.ticket_booking_system.services;

import org.lld.ticket_booking_system.interfaces.ISeatLockProvider;
import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Show;

import java.util.List;

public class SeatAvailabityService {
    private BookingService bookingService;
    private ISeatLockProvider seatLockProvider;

    public SeatAvailabityService(ISeatLockProvider seatLockProvider,BookingService bookingService){
        this.bookingService=bookingService;
        this.seatLockProvider=seatLockProvider;
    }

    public List<Seat> getAvailableSeat(Show show){
        List<Seat> allSeats=show.getScreen().getSeats();
        allSeats.remove(getUnavailableSeats(show));
        return allSeats;
    }

    public List<Seat> getUnavailableSeats(Show show){
        List<Seat> seats=bookingService.getBookedSeat(show);
        seats.addAll(seatLockProvider.getLockedSeats(show));

        return seats;
    }
}
