package org.lld.ticket_booking_system.interfaces;

import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Show;
import org.lld.ticket_booking_system.models.User;

import java.util.List;

public interface ISeatLockProvider {
    void lock(User user, Show show, List<Seat> seats) throws Exception;
    void unlock(User user,Show show,List<Seat> seats);
    boolean validateLock(User user,Show show,Seat seat);
    List<Seat> getLockedSeats(Show show);
}
