package org.lld.ticket_booking_system.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {
    private final int id;
    private final  String name;
    private final Theatre theatre;
    private List<Seat> seats;

    public Screen(int id,String name,Theatre theatre){
        this.id=id;
        this.name=name;
        this.theatre=theatre;
        seats=new ArrayList<>();
    }

    public void addSeat(Seat seat){
        seats.add(seat);
    }

    public void removeSeat(Seat seat){
        seats.remove(seat);
    }

}
