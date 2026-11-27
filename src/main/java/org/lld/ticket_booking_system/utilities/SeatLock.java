package org.lld.ticket_booking_system.utilities;

import lombok.Getter;
import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Show;
import org.lld.ticket_booking_system.models.User;

import java.time.Instant;
import java.util.Date;

@Getter
public class SeatLock {
    private Show show;
    private Seat seat;
    private User lockedBy;
    private Date lockTime;
    private Integer expirationTime; // In Second

    public SeatLock(Show show,Seat seat,User lockedBy,Date lockTime,Integer expirationTime){
        this.show=show;
        this.seat=seat;
        this.lockedBy=lockedBy;
        this.expirationTime=expirationTime;
        this.lockTime=lockTime;
    }

    public boolean isLockExpired(){
        final Instant lockInstant=lockTime.toInstant().plusSeconds(expirationTime);
        final Instant currentInstant=new Date().toInstant();

        return lockInstant.isBefore(currentInstant);
    }
}
