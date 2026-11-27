package org.lld.ticket_booking_system.services;

import org.lld.ticket_booking_system.models.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieService {
    private final AtomicInteger counter;
    private final Map<Integer, Movie> movies;

    public MovieService(){
        counter=new AtomicInteger();
        movies=new HashMap<>();
    }

    public Movie getMovieById(int movieId) throws Exception{
        if(!movies.containsKey(movieId)){
            throw new Exception("Movie with ID " + movieId + " not found.");
        }

        return movies.get(movieId);
    }

    public Movie createMovie(String name,int durationInMinutes){
        int id=counter.incrementAndGet();
        Movie movie=new Movie(id,name,durationInMinutes);

        movies.put(id,movie);

        return movie;
    }
}
