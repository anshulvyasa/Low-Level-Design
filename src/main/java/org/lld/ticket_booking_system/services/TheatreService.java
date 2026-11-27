package org.lld.ticket_booking_system.services;

import org.lld.ticket_booking_system.enums.SeatCategory;
import org.lld.ticket_booking_system.models.Screen;
import org.lld.ticket_booking_system.models.Seat;
import org.lld.ticket_booking_system.models.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TheatreService {
    private final AtomicInteger theatreCounter;
    private final AtomicInteger screenCounter;
    private final AtomicInteger seatCounter;

    private final Map<Integer, Theatre> theatreMap;
    private final Map<Integer, Screen> screenMap;
    private final Map<Integer, Seat> seatMap;

    public TheatreService(){
        theatreCounter=new AtomicInteger(0);
        screenCounter=new AtomicInteger(0);
        seatCounter=new AtomicInteger(0);

        theatreMap=new HashMap<>();
        screenMap=new HashMap<>();
        seatMap=new HashMap<>();
    }

    public Theatre getTheatreById(int theatreId) throws Exception{
       if(!theatreMap.containsKey(theatreId))  throw new Exception("Theatre doesn't exist");
       return theatreMap.get(theatreId);
    }

    public Screen getScreenById(int screenId) throws Exception{
        if(!screenMap.containsKey(screenId)) throw new Exception("Screen doesn't exist");
        return screenMap.get(screenId);
    }

    public Seat getSeatById(int seatId) throws Exception{
       if(!seatMap.containsKey(seatId)) throw new Exception("Seat doesn't exist");
       return seatMap.get(seatId);
    }

    public Theatre createTheatre(String name){
        int id=theatreCounter.incrementAndGet();
        Theatre theatre=new Theatre(id,name);

        theatreMap.put(id,theatre);

        return  theatre;
    }

    public Screen createScreen(String name,Theatre theatre){
        int id=screenCounter.incrementAndGet();

        Screen screen=new Screen(id,name,theatre);

        screenMap.put(id,screen);
        theatre.addScreen(screen);

        return screen;
    }

    public Seat createSeat(int rowNumber, SeatCategory seatCategory,Screen screen){
       int id=screenCounter.incrementAndGet();

       Seat seat=new Seat(id,rowNumber,seatCategory,screen);

       seatMap.put(id,seat);
       screen.addSeat(seat);

       return seat;
    }
}
