package org.lld.ticket_booking_system.models;

import lombok.Getter;
import lombok.Setter;
import org.lld.ticket_booking_system.enums.SeatCategory;

@Getter
public class Seat {
    private final int seatId;
    private final int rowNumber;
    private final Screen screen;

    @Setter
    private SeatCategory seatCategory;

    public Seat(int seatId,int rowNumber,SeatCategory seatCategory,Screen screen){
        this.seatId=seatId;
        this.rowNumber=rowNumber;
        this.seatCategory=seatCategory;
        this.screen=screen;
    }
}
