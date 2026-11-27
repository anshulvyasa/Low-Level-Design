package org.lld.ticket_booking_system.services;

import org.lld.ticket_booking_system.models.Movie;
import org.lld.ticket_booking_system.models.Screen;
import org.lld.ticket_booking_system.models.Show;
import org.lld.ticket_booking_system.models.Theatre;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowService {
    private AtomicInteger showCounter;
    private Map<Integer, Show> showMap;

    public ShowService(){
        showCounter=new AtomicInteger(0);
        showMap=new HashMap<>();
    }

    public Show getShow(int showId) throws Exception{
        if(!showMap.containsKey(showId)) throw new Exception("No Such Show Exist");
        return showMap.get(showId);
    }

    public Show createShow(Movie movie, Theatre theatre, Screen screen, Date startTime,int timeInDuration){
        int id=showCounter.incrementAndGet();

        Show show=new Show(id,movie,theatre,screen,startTime,timeInDuration);
        showMap.put(id,show);

        return show;
    }

    public List<Show> getAllShowForScreen(Screen screen){
        List<Show> shows=new ArrayList<>();

        for(Show show:showMap.values()){
            if(show.getScreen().equals(screen)) shows.add(show);
        }

        return shows;
    }
}
