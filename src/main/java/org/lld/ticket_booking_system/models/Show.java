package org.lld.ticket_booking_system.models;

import lombok.Getter;

import java.util.Date;

@Getter
public class Show {
    private int id;
    private Movie movie;
    private  Theatre theatre;
    private Screen screen;
    private Date startTime;
    private Integer timeInDuration;

    public Show(int id,Movie movie,Theatre theatre,Screen screen,Date startTime,Integer timeInDuration){
        this.id=id;
        this.movie=movie;
        this.theatre=theatre;
        this.screen=screen;
        this.startTime=startTime;
        this.timeInDuration=timeInDuration;
    }
}
