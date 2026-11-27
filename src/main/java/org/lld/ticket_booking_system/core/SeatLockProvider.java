package org.lld.ticket_booking_system.core;

import org.lld.ticket_booking_system.interfaces.ISeatLockProvider;
import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Show;
import org.lld.ticket_booking_system.models.User;
import org.lld.ticket_booking_system.utilities.SeatLock;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatLockProvider implements ISeatLockProvider {
    Map<Show,Map<Seat, SeatLock>> locks;
    private Integer lockTimeOut;

    public SeatLockProvider(Integer lockTimeOut){
        this.lockTimeOut=lockTimeOut;
        locks=new ConcurrentHashMap<>();
    }

    @Override
    public void lock(User user, Show show, List<Seat> seats) throws Exception {
        Map<Seat,SeatLock> seatLockMap=locks.computeIfAbsent(show,k->new ConcurrentHashMap<>());

        synchronized (seatLockMap){
            // validating if any seat is locked
            for (Seat seat:seats){
                SeatLock seatLock=seatLockMap.get(seat);

                if(seatLock!=null&&!seatLock.isLockExpired()){
                    throw new Exception("You can't lock these seats");
                }
            }

            // creating lock
            Date now=new Date();
            for(Seat seat:seats){
                SeatLock seatLock=new SeatLock(show,seat,user,now,lockTimeOut);
                seatLockMap.put(seat,seatLock);
            }
        }
    }

    @Override
    public void unlock(User user, Show show, List<Seat> seats) {
        Map<Seat,SeatLock> seatLockMap=locks.get(show);

        if(seatLockMap==null) return;

        synchronized (seatLockMap){
            for(Seat seat:seats){
                SeatLock seatLock=seatLockMap.get(seat);

                if(seatLock!=null&&seatLock.getLockedBy().equals(user)){
                    seatLockMap.remove(seat);
                }
            }
        }
    }

    @Override
    public boolean validateLock(User user, Show show, Seat seat) {
        Map<Seat,SeatLock> seatLockMap=locks.get(show);
        if(seatLockMap==null) return  false;

        synchronized (seatLockMap){
            SeatLock seatLock=seatLockMap.get(seat);

            return seatLock!=null&&!seatLock.isLockExpired()&&seatLock.getLockedBy().equals(user);
        }
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat,SeatLock> seatLockMap=locks.get(show);
        if(seatLockMap==null) return Collections.emptyList();

        synchronized (seatLockMap){
            return seatLockMap.entrySet().stream()
                    .filter(entry->!entry.getValue().isLockExpired())
                    .map(Map.Entry::getKey).collect(Collectors.toList());
        }
    }
}
